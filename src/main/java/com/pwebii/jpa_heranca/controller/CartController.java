package com.pwebii.jpa_heranca.controller;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pwebii.jpa_heranca.model.entity.Product;
import com.pwebii.jpa_heranca.model.entity.Sale;
import com.pwebii.jpa_heranca.model.repository.PersonRepository;
import com.pwebii.jpa_heranca.model.repository.ProductRepository;
import com.pwebii.jpa_heranca.model.repository.SaleRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Controller
@RequestMapping("cart")
@Transactional
@Scope("request")
public class CartController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private Sale sale;

    @GetMapping
    public ModelAndView viewCart() {
        return new ModelAndView("cart/cart");
    }

    @GetMapping("/add/{id}")
    public ModelAndView addToCart(@PathVariable("id") Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new NoSuchElementException("Product not found"));
        sale.addItem(product, 1);
        return new ModelAndView("redirect:/cart");
    }

    @GetMapping("/remove/{id}")
    public ModelAndView removeFromCart(@PathVariable("id") Long productId) {
        sale.removeItem(productId, 1);
        return new ModelAndView("redirect:/cart");
    }


    @GetMapping("/delete/{id}")
    public ModelAndView deleteFromCart(@PathVariable("id") Long productId) {
        sale.removeItem(productId);
        return new ModelAndView("redirect:/cart");
    }

    @GetMapping("/clear")
    public String clearCart() {
        sale.clear();
        return "redirect:/product";
    }

    @GetMapping("/finish")
    public ModelAndView finish(HttpSession session) {
            
        sale.setDate(LocalDate.now());
        sale.setClient(personRepository.findById(Long.valueOf(11)).orElseThrow(() -> new NoSuchElementException("Person not found")));
        saleRepository.save(sale);
        session.setAttribute("sale", new Sale());
        return new ModelAndView("redirect:/success");


    }
}
