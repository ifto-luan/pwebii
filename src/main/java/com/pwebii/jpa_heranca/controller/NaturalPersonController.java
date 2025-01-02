package com.pwebii.jpa_heranca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pwebii.jpa_heranca.model.entity.NaturalPerson;
import com.pwebii.jpa_heranca.model.repository.NaturalPersonRepository;

import jakarta.validation.Valid;


@Controller
@RequestMapping("admin/natural-person")
public class NaturalPersonController {

    @Autowired
    private NaturalPersonRepository repo;

    @GetMapping("/new")
    public ModelAndView newNaturalPerson(NaturalPerson p) {
        return new ModelAndView("admin/person/natural-person");
    }
    
    @PostMapping("/save")
    public ModelAndView saveJuridicalPerson(@Valid NaturalPerson p, BindingResult result) {

        if (result.hasErrors())
            return newNaturalPerson(p);

        p.setCpf(p.getCpf().replaceAll("\\D", ""));
        repo.save(p);
        return new ModelAndView("redirect:/person");
    }
}
