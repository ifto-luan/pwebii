package com.pwebii.jpa_heranca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pwebii.jpa_heranca.model.entity.Product;
import com.pwebii.jpa_heranca.model.repository.ProductRepository;

import jakarta.validation.Valid;



@Controller
@RequestMapping("admin/product")
public class ProductController {

    @Autowired
    private ProductRepository repo;

    @GetMapping
    public ModelAndView listProducts(ModelMap model) {
        model.addAttribute("products", repo.findAll());
        model.addAttribute("customPageTitle", "Product Management");
        return new ModelAndView("admin/product/product-list");
    }

    @GetMapping("/{id}")
    public ModelAndView getProduct(@PathVariable Long id, ModelMap model) {

        model.addAttribute("product", repo.findById(id));
        return new ModelAndView("admin/product/product");

    }

    @GetMapping("/search")
    public ModelAndView findByDescription(@RequestParam(required = false) String description, ModelMap model) {

        List<Product> products;

        if (description != null) {

            if (!description.isEmpty()) {

                products = repo.findAllContainingDescription(description);
            
            } else {
                return new ModelAndView("redirect:/admin/product");
            }


        } else {

            products = repo.findAll();

        }

        model.addAttribute("products", products);
        model.addAttribute("searchTerm", description);
        return new ModelAndView("admin/product/product-list");

    }
    

    @GetMapping("/new")
    public ModelAndView newProduct(Product p) {
        return new ModelAndView("admin/product/product");
    }
    
    @PostMapping("/save")
    public ModelAndView saveProduct(@Valid Product p, BindingResult result) {
        
        if (result.hasErrors())
            return newProduct(p);

        repo.save(p);
        // flash.addFlashAttribute("successMessage", "Produto salvo com sucesso");
        return new ModelAndView("redirect:/admin/product");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        repo.deleteById(id);
        return  new ModelAndView("redirect:/admin/product");

    }

}

