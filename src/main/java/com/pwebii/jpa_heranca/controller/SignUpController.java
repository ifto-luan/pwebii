package com.pwebii.jpa_heranca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pwebii.jpa_heranca.model.dto.UserClientDTO;
import com.pwebii.jpa_heranca.model.entity.Client;
import com.pwebii.jpa_heranca.model.entity.UserImpl;
import com.pwebii.jpa_heranca.model.repository.ClientRepository;
import com.pwebii.jpa_heranca.model.repository.RoleRepository;
import com.pwebii.jpa_heranca.model.repository.UserRepository;

import jakarta.validation.Valid;


@Controller
@RequestMapping("signup")
public class SignUpController {

    @Autowired
    UserRepository userRepo;
    
    @Autowired
    ClientRepository clientRepo;
    
    @Autowired
    RoleRepository roleRepo;

    @Autowired
    PasswordEncoder passwordEncoder;
 
    @GetMapping
    public ModelAndView signup(UserClientDTO userClientDTO) {
        return new ModelAndView("user/user");
    }
    
    @PostMapping("save")
    public ModelAndView save(@Valid UserClientDTO userClientDTO, RedirectAttributes redirectAttributes, BindingResult result) {

        if (result.hasErrors())
            return signup(userClientDTO);

        UserImpl newUser = userClientDTO.getUser();
        System.out.println(newUser);

        Client savedClient = clientRepo.save(userClientDTO.getClient());
        
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setClient(savedClient);
        newUser.addRole(roleRepo.findByDescription("ROLE_USER"));
        userRepo.save(newUser);

        System.out.println(savedClient);
        
        redirectAttributes.addFlashAttribute("successMessage", "Registration successfull. Please login!");

        return new ModelAndView("redirect:/login");

    }

}
