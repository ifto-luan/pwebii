package com.pwebii.jpa_heranca.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public ModelAndView signup() {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            ModelAndView mav = new ModelAndView("layout/signup");
            mav.addObject("userClientDTO", new UserClientDTO()); 
            return mav;
        }

        return new ModelAndView("redirect:/home");

    }

    @PostMapping("save")
    public ModelAndView save(@Valid UserClientDTO userClientDTO, BindingResult result,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {

            ModelAndView mav = new ModelAndView("layout/signup");
            mav.addObject("userClientDTO", userClientDTO);
            mav.addObject("errors", result);
            return mav;
        }

        UserImpl newUser = userClientDTO.toUser();
        Client savedClient = clientRepo.save(userClientDTO.toClient());

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setClient(savedClient);
        newUser.addRole(roleRepo.findByDescription("ROLE_USER").orElseThrow(() -> new NoSuchElementException("User role not found")));
        userRepo.save(newUser);

        redirectAttributes.addFlashAttribute("successMessage", "Registration successful. Please login!");
        return new ModelAndView("redirect:/login");
    }

}
