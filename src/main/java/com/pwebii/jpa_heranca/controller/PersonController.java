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
@RequestMapping("admin/person")
public class PersonController {

    @Autowired
    private PersonRepository repo;

    @GetMapping
    public ModelAndView listClients(ModelMap model) {
        
        model.addAttribute("people", repo.findAll());
        return new ModelAndView("admin/person/person-list");
    }

    @GetMapping("/{id}")
    public ModelAndView getPerson(@PathVariable Long id, ModelMap model) {
        
        Person p = repo.findById(id).orElseThrow(() -> new NoSuchElementException("Person not found"));
        
        if (p.isJuridicalPerson()) {
            model.addAttribute("juridicalPerson", p);
            return new ModelAndView("admin/person/juridical-person");
            
        }
        
        if (p.isNaturalPerson()) {
            model.addAttribute("naturalPerson", p);
            return new ModelAndView("admin/person/natural-person");
        }

        return new ModelAndView("redirect:/admin/person");

    }

    @GetMapping("/search")
    public ModelAndView findByName(@RequestParam(required = false) String name, ModelMap model) {
    
        List<Person> people;

        if (name != null) {

            if (!name.isEmpty()) {

                people = repo.findAllContainingName(name);
            
            } else {

                return new ModelAndView("redirect:/admin/person");

            }

        } else {
            people = repo.findAll();
        }

        model.addAttribute("people", people);
        model.addAttribute("searchName", name);

        return new ModelAndView("admin/person/person-list");


    }
    

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        repo.deleteById(id);
        return  new ModelAndView("redirect:/admin/person");

    }

    public static String applyDocumentMask(String rawDocument) {
        if (rawDocument.length() == 11) {
            
            return rawDocument.replaceAll("(\\d{3})(\\d)", "$1.$2")
                               .replaceAll("(\\d{3})(\\d)", "$1.$2")
                               .replaceAll("(\\d{3})(\\d{1,2})$", "$1-$2");
        } else if (rawDocument.length() == 14) {
            
            return rawDocument.replaceAll("(\\d{2})(\\d)", "$1.$2")
                               .replaceAll("(\\d{3})(\\d)", "$1.$2")
                               .replaceAll("(\\d{3})(\\d)", "$1/$2")
                               .replaceAll("(\\d{4})(\\d{1,2})$", "$1-$2");
        } else {
            
            throw new IllegalArgumentException("Invalid CPF or CNPJ length");
        }
    }
    

}
