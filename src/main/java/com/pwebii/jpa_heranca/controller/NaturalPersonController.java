package com.pwebii.jpa_heranca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pwebii.jpa_heranca.model.entity.NaturalPerson;
import com.pwebii.jpa_heranca.model.repository.NaturalPersonRepository;


@Controller
@RequestMapping("natural-person")
public class NaturalPersonController {

    @Autowired
    private NaturalPersonRepository repo;

    @GetMapping("/new")
    public ModelAndView newNaturalPerson(ModelMap model) {
        model.addAttribute("person", new NaturalPerson());
        return new ModelAndView("./person/person");
    }
    
    @PostMapping("/save")
    public ModelAndView saveJuridicalPerson(NaturalPerson p) {
        repo.save(p);
        return new ModelAndView("redirect:/person");
    }
}
