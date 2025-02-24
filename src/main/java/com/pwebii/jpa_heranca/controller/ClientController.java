package com.pwebii.jpa_heranca.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pwebii.jpa_heranca.model.entity.Client;
import com.pwebii.jpa_heranca.model.entity.Order;
import com.pwebii.jpa_heranca.model.repository.ClientRepository;
import com.pwebii.jpa_heranca.model.repository.OrderRepository;

import jakarta.validation.Valid;



@Controller
@RequestMapping("admin/client")
public class ClientController {

    @Autowired
    private ClientRepository clientRepo;

    @Autowired
    private OrderRepository orderRepo;

    @GetMapping
    public ModelAndView listClients(ModelMap model) {
        
        model.addAttribute("clients", clientRepo.findAll());
        return new ModelAndView("admin/client/client-list");
    }
    
    @GetMapping("/new")
    public ModelAndView newClient(Client c) {
        return new ModelAndView("admin/client/client");
    }
    
    
    @PostMapping("/save")
    public ModelAndView saveclient(@Valid Client c, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors())
            return newClient(c);
        
        redirectAttributes.addFlashAttribute("successMessage", c.getId() == null ? "Client registered successfully" : "Client updated successfully");
        clientRepo.save(c);
        return new ModelAndView("redirect:/admin/client");


    }
    
    @GetMapping("/{id}")
    public ModelAndView getClient(@PathVariable Long id, ModelMap model) {
        
        Client c = clientRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Client not found"));
        model.addAttribute("client", c);
        return new ModelAndView("admin/client/client");

    }
    
    @GetMapping("/client_orders/{id}")
    public ModelAndView getClientOrders(@PathVariable Long id, RedirectAttributes redirectAttributes, ModelMap model) {

        Client c = clientRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Client not found"));;
        List<Order> clientOrders = orderRepo.findByClientId(c.getId());

        if (clientOrders.isEmpty()) {

            redirectAttributes.addFlashAttribute("errorMessage", "The client doesn't have any orders!");
            return new ModelAndView("redirect:/admin/client");

        }

        model.addAttribute("orders", clientOrders);
        return new ModelAndView("admin/client/client-orders");

    }

    @GetMapping("/search")
    public ModelAndView findByName(@RequestParam(required = false) String name, ModelMap model) {
    
        List<Client> clients;

        if (name != null) {

            if (!name.isEmpty()) {

                clients = clientRepo.findAllContainingName(name);
            
            } else {

                return new ModelAndView("redirect:/admin/client");

            }

        } else {

            clients = clientRepo.findAll();
        }

        clients.forEach(System.out::println);
        model.addAttribute("clients", clients);
        model.addAttribute("searchName", name);

        return new ModelAndView("admin/client/client-list");


    }
    

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        clientRepo.deleteById(id);
        return  new ModelAndView("redirect:/admin/client");

    }

}
