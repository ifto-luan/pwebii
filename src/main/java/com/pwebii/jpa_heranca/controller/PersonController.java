package com.pwebii.jpa_heranca.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pwebii.jpa_heranca.model.entity.Person;
import com.pwebii.jpa_heranca.model.repository.PersonRepository;



@Controller
@RequestMapping("person")
public class PersonController {

    @Autowired
    private PersonRepository repo;

    @GetMapping
    public ModelAndView listClients(ModelMap model) {
        
        model.addAttribute("people", repo.findAll());
        return new ModelAndView("person/person-list");
    }

    @GetMapping("/{id}")
    public ModelAndView getPerson(@PathVariable Long id, ModelMap model) {
        
        Person p = repo.findById(id).orElseThrow(() -> new NoSuchElementException("Person not found"));
        model.addAttribute("person", p);
        return new ModelAndView("person/person");

    }

    @GetMapping("/search")
    public ModelAndView findByName(@RequestParam(name = "name", required = false) String name, ModelMap model) {
    
        List<Person> people;

        if (name != null && !name.isEmpty()) {
            people = repo.findAllContainingName(name);
        } else {
            people = repo.findAll();
        }

        model.addAttribute("people", people);
        model.addAttribute("searchName", name);

        return new ModelAndView("person/person-list");


    }
    

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        repo.deleteById(id);
        return  new ModelAndView("redirect:/person");

    }
    

}
