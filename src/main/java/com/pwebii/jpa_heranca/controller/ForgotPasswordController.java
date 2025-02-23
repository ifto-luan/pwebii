package com.pwebii.jpa_heranca.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pwebii.jpa_heranca.model.entity.Client;
import com.pwebii.jpa_heranca.model.entity.UserImpl;
import com.pwebii.jpa_heranca.model.repository.ClientRepository;
import com.pwebii.jpa_heranca.model.repository.UserRepository;

@Controller
@RequestMapping("forgot-password")
public class ForgotPasswordController {

    @Autowired
    UserRepository userRepo;

    @Autowired
    ClientRepository clientRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping
    public ModelAndView forgotPassword() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return new ModelAndView("/layout/forgot-password");
        }

        return new ModelAndView("redirect:/home");

    }

    @PostMapping
    public ModelAndView changePassword(@RequestParam String identifier, @RequestParam String password,
            RedirectAttributes redirectAttributes) {

        try {

            Client c = clientRepo.findByIdentifier(identifier)
                    .orElseThrow(() -> new NoSuchElementException("There is no client with this CPF/CNPJ"));

            UserImpl u = userRepo.findByClient(c).orElseThrow(() -> new NoSuchElementException("There is no user associated with this CPF/CNPJ"));

            u.setPassword(passwordEncoder.encode(password));
            userRepo.save(u);
            redirectAttributes.addFlashAttribute("successMessage", "Password changed. Please login!");
            return new ModelAndView("redirect:/login");

        } catch (NoSuchElementException e) {

            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return new ModelAndView("redirect:/forgot-password");

        }

    }

}
