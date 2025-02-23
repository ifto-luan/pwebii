package com.pwebii.jpa_heranca.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pwebii.jpa_heranca.model.entity.Address;
import com.pwebii.jpa_heranca.model.entity.UserImpl;
import com.pwebii.jpa_heranca.model.repository.AddressRepository;
import com.pwebii.jpa_heranca.model.repository.ClientRepository;
import com.pwebii.jpa_heranca.model.repository.UserRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/address")
public class AddressController {

    @Autowired
    UserRepository userRepo;

    @Autowired
    ClientRepository clientRepo;

    @Autowired
    AddressRepository addressRepo;

    @GetMapping
    public ModelAndView getAddressList(@AuthenticationPrincipal(expression = "username") String username) {

        UserImpl user = userRepo.findByUsername(username)
        .orElseThrow(() -> new NoSuchElementException("User not found"));
        List<Address> addressList = addressRepo.findByClient(user.getClient());

        if (addressList.isEmpty())
            return new ModelAndView("user/address/no-address");

        ModelAndView model = new ModelAndView("user/address/address-list");
        model.addObject("addressList", addressList);
        return model;

    }

    @GetMapping("new")
    public ModelAndView newAddress(Address address) {

        return new ModelAndView("user/address/address");

    }

    @GetMapping("{id}")
    public ModelAndView getAddress(@PathVariable Long id, ModelMap model) {

        Address address = addressRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Address not found"));
        model.addAttribute("address", address);
        return new ModelAndView("user/address/address");
    }

    @PostMapping("save")
    public ModelAndView saveAddress(@Valid Address address, BindingResult result, RedirectAttributes redirectAttributes, @AuthenticationPrincipal(expression = "username") String username) {

        UserImpl user = userRepo.findByUsername(username).orElseThrow(() -> new NoSuchElementException("User not found"));

        if (result.hasErrors())
            return newAddress(address);
        
        address.setClient(user.getClient());
        addressRepo.save(address);
        redirectAttributes.addFlashAttribute("successMessage", address.getId() == null ? "Address added successfully" : "Address updated successfully");
        return new ModelAndView("redirect:/user/address");

    }

}
