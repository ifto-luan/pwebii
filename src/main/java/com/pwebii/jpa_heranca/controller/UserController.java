package com.pwebii.jpa_heranca.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
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

import com.pwebii.jpa_heranca.model.dto.UserClientDTO;
import com.pwebii.jpa_heranca.model.entity.Client;
import com.pwebii.jpa_heranca.model.entity.Order;
import com.pwebii.jpa_heranca.model.entity.UserImpl;
import com.pwebii.jpa_heranca.model.repository.AddressRepository;
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
    AddressRepository addressRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping
    public ModelAndView getUserInfo(@AuthenticationPrincipal(expression = "username") String username) {

        ModelAndView model = new ModelAndView("user/user");
        UserImpl u = userRepo.findByUsername(username).orElseThrow(() -> new NoSuchElementException("User not found"));
        Client c = clientRepo.findById(u.getClient().getId())
                .orElseThrow(() -> new NoSuchElementException("Client not found"));
        model.addObject("userClientDTO", UserClientDTO.fromEntities(u, c));

        return model;

    }

    @GetMapping("change-password")
    public ModelAndView changePassword(@AuthenticationPrincipal(expression = "username") String username) {

        ModelAndView model = new ModelAndView("user/change-password");
        UserImpl user = userRepo.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        model.addObject("user", user);
        return model;

    }

    @PostMapping("change-password")
    public ModelAndView changePassword(@RequestParam String password, @RequestParam String passwordConfirm,
            @AuthenticationPrincipal(expression = "username") String username, RedirectAttributes redirectAttributes) {

        UserImpl user = userRepo.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        if ((password != null && !password.isBlank()) && (passwordConfirm != null && !passwordConfirm.isBlank())) {

            if (password.equals(passwordConfirm)) {

                user.setPassword(passwordEncoder.encode(passwordConfirm));
                userRepo.save(user);
                redirectAttributes.addFlashAttribute("successMessage", "Password updatede!");
                return new ModelAndView("redirect:/user");

            } else {

                redirectAttributes.addFlashAttribute("errorMessage", "Passwords should match!");
                return new ModelAndView("redirect:/user/change-password");

            }

        } else {

            redirectAttributes.addFlashAttribute("errorMessage", "Password must not be blank!");
            return new ModelAndView("redirect:/user/change-password");

        }

    }

    @PostMapping("save")
    public ModelAndView save(@Valid UserClientDTO userClientDTO, BindingResult result,
            RedirectAttributes redirectAttributes, @AuthenticationPrincipal(expression = "username") String username) {

        if (result.hasErrors()) {

            ModelAndView mav = new ModelAndView("user/user");
            mav.addObject("userClientDTO", userClientDTO);
            mav.addObject("org.springframework.validation.BindingResult.userClientDTO", result);
            return mav;

        }

        UserImpl user = userRepo.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        user.getClient().setName(userClientDTO.getName());
        user.getClient().setIdentifier(userClientDTO.getIdentifier());
        user.getClient().setType(userClientDTO.getType());

        userRepo.save(user);

        redirectAttributes.addFlashAttribute("successMessage", "Changes applied!");
        return new ModelAndView("redirect:/user");

    }

    @GetMapping("/orders")
    public ModelAndView getUserOrders(ModelMap model,
            @AuthenticationPrincipal(expression = "username") String username) {

        if (username == null) {
            return new ModelAndView("redirect:/home");
        }

        UserImpl u = userRepo.findByUsername(username).orElseThrow(() -> new NoSuchElementException("User not found"));

        if (u != null) {

            List<Order> orders = orderRepo.findByClientId(u.getClient().getId());
            model.addAttribute("orders", orders);
            return orders.size() > 0 ? new ModelAndView("user/order/user-orders") : new ModelAndView("user/order/no-orders");

        }

        return new ModelAndView("user/user");

    }

    @GetMapping("/orders/{id}")
    public ModelAndView getOrder(@PathVariable Long id, ModelMap model) {
        model.addAttribute("order",
                orderRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Order not found")));
        return new ModelAndView("user/order/order", model);
    }

}
