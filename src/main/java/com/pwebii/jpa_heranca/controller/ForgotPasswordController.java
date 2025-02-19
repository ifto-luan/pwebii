package com.pwebii.jpa_heranca.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

        return new ModelAndView("/user/forgot-password");

    }

    @PostMapping
    public ModelAndView changePassword(@RequestParam String identifier,@RequestParam String password, RedirectAttributes redirectAttributes) {

        Client c = clientRepo.findByIdentifier(identifier);
        
        if (c != null) {
            
            UserImpl u = userRepo.findByClient(c);

            if (u != null) {
                
                u.setPassword(passwordEncoder.encode(password));
                userRepo.save(u);
                redirectAttributes.addFlashAttribute("successMessage", "Password changed. Please login!");
                return new ModelAndView("redirect:/login");

                
            } else {

                redirectAttributes.addFlashAttribute("errorMessage", "There is no user related with this CPF/CNPJ");
                return new ModelAndView("redirect:/forgot-password");
                
            }
        
            
        } else {
            
            redirectAttributes.addFlashAttribute("errorMessage", "There is no user with this CPF/CNPJ");
            return new ModelAndView("redirect:/forgot-password");

        }




    }
    
}
