package com.pwebii.jpa_heranca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.pwebii.jpa_heranca.model.entity.Product;
import com.pwebii.jpa_heranca.model.entity.Sale;
import com.pwebii.jpa_heranca.model.repository.ProductRepository;

@Controller
@RequestMapping("cart")
@SessionAttributes("cart")
public class CartController {

    @Autowired
    private ProductRepository repo;
    
    @ModelAttribute("cart")
    public Sale cart() {
        return new Sale();
    }

    @GetMapping
    public String viewCart(@ModelAttribute("cart") Sale cart, ModelMap model) {
        model.addAttribute("cartItems", cart.getItems());
        model.addAttribute("totalAmount", cart.calculateTotal());
        return "";
    }

    @PostMapping("/add")
    public String addToCart(@ModelAttribute("cart") Sale cart, @RequestParam Long productId, @RequestParam int quantity) {
        Product product = repo.findById(productId).orElseThrow(() -> new NoSuchElementException("Sell not found")));
        cart.addItem(product, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/remove")
    public String removeFromCart(@ModelAttribute("cart") Sale cart, @RequestParam Long productId) {
        cart.removeItem(productId);
        return "redirect:/cart";
    }

    @PostMapping("/clear")
    public String clearCart(@ModelAttribute("cart") Sale cart) {
        cart.clear();
        return "redirect:/cart";
    }

}
