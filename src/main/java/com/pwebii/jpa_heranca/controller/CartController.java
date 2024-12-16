package com.pwebii.jpa_heranca.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.pwebii.jpa_heranca.model.entity.Product;
import com.pwebii.jpa_heranca.model.entity.Sale;
import com.pwebii.jpa_heranca.model.repository.ProductRepository;

@Controller
@RequestMapping("cart")
@SessionAttributes("sale")
public class CartController {

    @Autowired
    private ProductRepository repo;
    
    @ModelAttribute("sale")
    public Sale cart() {
        return new Sale();
    }

    @GetMapping
    public ModelAndView viewCart(@ModelAttribute("sale") Sale cart, ModelMap model) {
        return new ModelAndView("sale/sale");
    }

    @GetMapping("/add/{id}")
    public ModelAndView addToCart(@ModelAttribute("sale") Sale cart, @PathVariable("id") Long productId) {
        Product product = repo.findById(productId).orElseThrow(() -> new NoSuchElementException("Product not found"));
        cart.addItem(product, 1);
        return new ModelAndView("redirect:/cart");
    }

    @PostMapping("/remove")
    public String removeFromCart(@ModelAttribute("sale") Sale cart, @RequestParam Long productId) {
        cart.removeItem(productId);
        return "redirect:/cart";
    }

    @PostMapping("/clear")
    public String clearCart(@ModelAttribute("sale") Sale cart) {
        cart.clear();
        return "redirect:/cart";
    }

}
