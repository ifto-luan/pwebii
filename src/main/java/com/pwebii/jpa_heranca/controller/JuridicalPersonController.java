package com.pwebii.jpa_heranca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pwebii.jpa_heranca.model.entity.JuridicalPerson;
import com.pwebii.jpa_heranca.model.repository.JuridicalPersonRepository;


@Controller
@RequestMapping("juridical-person")
public class JuridicalPersonController {

    @Autowired
    private JuridicalPersonRepository repo;

    @GetMapping("/new")
    public ModelAndView newJuridicalPerson(ModelMap model) {
        model.addAttribute("person", new JuridicalPerson());
        return new ModelAndView("./person/person");
    }

    @PostMapping("/save")
    public ModelAndView saveJuridicalPerson(JuridicalPerson p) {
        repo.save(p);
        return new ModelAndView("redirect:/person");
    }
}
