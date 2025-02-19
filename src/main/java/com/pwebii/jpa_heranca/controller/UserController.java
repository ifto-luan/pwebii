package com.pwebii.jpa_heranca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pwebii.jpa_heranca.model.dto.UserClientDTO;
import com.pwebii.jpa_heranca.model.repository.ClientRepository;
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
    PasswordEncoder passwordEncoder;

    @GetMapping
    public ModelAndView signup(UserClientDTO userClientDTO) {
        return new ModelAndView("user/user");
    }
    
    @PostMapping("save")
    public ModelAndView save(@Valid UserClientDTO userClientDTO, RedirectAttributes redirectAttributes) {

        userClientDTO.getUser().setPassword(passwordEncoder.encode(userClientDTO.getUser().getPassword()));
        userRepo.save(userClientDTO.getUser());
        clientRepo.save(userClientDTO.getClient());

        redirectAttributes.addFlashAttribute("successMessage", "Registration successfull. Please login!");

        return new ModelAndView("redirect:/login");

    }
    
}
