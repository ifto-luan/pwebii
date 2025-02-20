package com.pwebii.jpa_heranca.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pwebii.jpa_heranca.model.dto.UserClientDTO;
import com.pwebii.jpa_heranca.model.entity.Client;
import com.pwebii.jpa_heranca.model.entity.Order;
import com.pwebii.jpa_heranca.model.entity.UserImpl;
import com.pwebii.jpa_heranca.model.repository.ClientRepository;
import com.pwebii.jpa_heranca.model.repository.OrderRepository;
import com.pwebii.jpa_heranca.model.repository.UserRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserRepository userRepo;

    @Autowired
    ClientRepository clientRepo;

    @Autowired
    OrderRepository orderRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping
    public ModelAndView getUserInfo(ModelMap model, @AuthenticationPrincipal(expression = "username") String username) {

        if (username == null) {
            return new ModelAndView("redirect:/home");
        }

        UserImpl u = userRepo.findByUsername(username);

        if (u != null) {

            UserClientDTO userClientDTO = new UserClientDTO();
            userClientDTO.setUser(u);
            userClientDTO.setClient(u.getClient());
            model.addAttribute("userClientDTO", userClientDTO);
            return new ModelAndView("user/user");
            
        }
        
        return new ModelAndView("user/user");
        
    }
    
    @GetMapping("/orders")
    public ModelAndView getUserOrders(ModelMap model, @AuthenticationPrincipal(expression = "username") String username) {

        if (username == null) {
            return new ModelAndView("redirect:/home");
        }
        
        UserImpl u = userRepo.findByUsername(username);
        
        
        if (u != null) {
            
            List<Order> orders = orderRepo.findByClientId(u.getClient().getId());
            model.addAttribute("orders", orders);
            return orders.size() > 0 ? new ModelAndView("user/user-orders") : new ModelAndView("user/no-orders");

        }

        return new ModelAndView("user/user");

    }

    @GetMapping("/orders/{id}")
    public ModelAndView getOrder(@PathVariable Long id, ModelMap model) {
        model.addAttribute("order", orderRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Sell not found")));
        return new ModelAndView("user/order", model);
    }

    @PostMapping("save")
    public ModelAndView save(@Valid UserClientDTO userClientDTO, RedirectAttributes redirectAttributes) {


        UserImpl user = userClientDTO.getUser();
        Client client = userClientDTO.getClient();

        String submitedPassword = user.getPassword();
        String actualPassword = userRepo.findById(user.getId()).orElseThrow(() -> new NoSuchElementException()).getPassword();

        if (submitedPassword.isBlank()) {

            user.setPassword(actualPassword);

        }

        userRepo.save(user);
        clientRepo.save(client);

        return new ModelAndView("redirect:/");

    }

}
