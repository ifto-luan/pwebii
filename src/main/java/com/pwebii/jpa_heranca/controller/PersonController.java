package com.pwebii.jpa_heranca.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pwebii.jpa_heranca.model.entity.Person;
import com.pwebii.jpa_heranca.model.repository.PersonRepository;


@Controller
@RequestMapping("person")
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @GetMapping
    public ModelAndView listClients(ModelMap model) {
        
        model.addAttribute("people", personRepository.findAll());
        return new ModelAndView("./person/person-list");
    }

    @GetMapping("/{id}")
    public ModelAndView getPerson(@PathVariable Long id, ModelMap model) {
        
        Person p = personRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Person not found"));
        model.addAttribute("person", p);
        return new ModelAndView("./person/person");

    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        personRepository.deleteById(id);
        return  new ModelAndView("redirect:/person");

    }
    

}
