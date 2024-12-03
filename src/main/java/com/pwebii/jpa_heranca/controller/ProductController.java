package com.pwebii.jpa_heranca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pwebii.jpa_heranca.model.entity.Product;
import com.pwebii.jpa_heranca.model.repository.ProductRepository;



@Controller
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductRepository repo;

    @GetMapping
    public ModelAndView listProducts(ModelMap model) {
        model.addAttribute("products", repo.findAll());
        return new ModelAndView("product/product-list");
    }

    @GetMapping("/{id}")
    public ModelAndView getProduct(@PathVariable Long id, ModelMap model) {

        model.addAttribute("product", repo.findById(id));
        return new ModelAndView("product/product");

    }

    @GetMapping("/search")
    public ModelAndView findByDescription(@RequestParam(name = "description", required = false) String description, ModelMap model) {

        List<Product> products;

        if (description != null && !description.isEmpty()) {

            products = repo.findAllContainingDescription(description);

        } else {

            products = repo.findAll();

        }

        model.addAttribute("products", products);
        model.addAttribute("searchTerm", description);
        return new ModelAndView("product/product-list");

    }
    

    @GetMapping("/new")
    public ModelAndView newProduct(ModelMap model) {
        model.addAttribute("product", new Product());
        return new ModelAndView("product/product");
    }
    
    @PostMapping("/save")
    public ModelAndView saveProduct(Product p) {
        repo.save(p);
        return new ModelAndView("redirect:/product");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        repo.deleteById(id);
        return  new ModelAndView("redirect:/product");

    }

}

