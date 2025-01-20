package com.pwebii.jpa_heranca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pwebii.jpa_heranca.model.entity.Product;
import com.pwebii.jpa_heranca.model.repository.ProductRepository;


@Controller
@RequestMapping("home")
public class HomeController {
    
    @Autowired
    private ProductRepository repo;

    @GetMapping
    public ModelAndView getMethodName(ModelMap model) {

        model.addAttribute("products", repo.findAll());
        return new ModelAndView("user/home");
    
    }
    
     @GetMapping("/search")
    public ModelAndView findByDescription(@RequestParam(required = false) String description, ModelMap model) {

        List<Product> products;

        if (description != null) {

            if (!description.isEmpty()) {

                products = repo.findAllContainingDescription(description);
            
            } else {
                return new ModelAndView("redirect:/home");
            }


        } else {

            products = repo.findAll();

        }

        model.addAttribute("products", products);
        model.addAttribute("searchTerm", description);
        return new ModelAndView("user/home");

    }

}
