package com.pwebii.jpa_heranca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pwebii.jpa_heranca.model.entity.JuridicalPerson;
import com.pwebii.jpa_heranca.model.repository.JuridicalPersonRepository;

import jakarta.validation.Valid;


@Controller
@RequestMapping("admin/juridical-person")
public class JuridicalPersonController {

    @Autowired
    private JuridicalPersonRepository repo;

    @GetMapping("/new")
    public ModelAndView newJuridicalPerson(JuridicalPerson p) {
        return new ModelAndView("admin/person/juridical-person");
    }

    @PostMapping("/save")
    public ModelAndView saveJuridicalPerson(@Valid JuridicalPerson p, BindingResult result) {

        if (result.hasErrors()) {
            return newJuridicalPerson(p);
        }
        
        repo.save(p);
        return new ModelAndView("redirect:/admin/person");
    }
}
