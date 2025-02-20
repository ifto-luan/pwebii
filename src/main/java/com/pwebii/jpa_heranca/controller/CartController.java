package com.pwebii.jpa_heranca.controller;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pwebii.jpa_heranca.model.entity.Order;
import com.pwebii.jpa_heranca.model.entity.Product;
import com.pwebii.jpa_heranca.model.repository.OrderRepository;
import com.pwebii.jpa_heranca.model.repository.ProductRepository;
import com.pwebii.jpa_heranca.model.repository.UserRepository;

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
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private Order order;

    @GetMapping
    public ModelAndView viewCart(ModelMap model) {
        model.addAttribute("customPageTitle", "Your Cart");
        return (order != null && order.getItems().size() > 0) ? new ModelAndView("user/cart/cart") : new ModelAndView("user/cart/empty-cart");
    }

    @GetMapping("/add/{id}")
    public ModelAndView addToCart(@PathVariable("id") Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new NoSuchElementException("Product not found"));
        order.addItem(product, 1);
        return new ModelAndView("redirect:/cart");
    }

    @GetMapping("/remove/{id}")
    public ModelAndView removeFromCart(@PathVariable("id") Long productId) {
        order.removeItem(productId, 1);
        return new ModelAndView("redirect:/cart");
    }


    @GetMapping("/delete/{id}")
    public ModelAndView deleteFromCart(@PathVariable("id") Long productId) {
        order.removeItem(productId);
        return new ModelAndView("redirect:/cart");
    }

    @GetMapping("/clear")
    public String clearCart() {
        order.clear();
        return "redirect:/home";
    }

    @GetMapping("/finish")
    public ModelAndView finish(HttpSession session, @AuthenticationPrincipal(expression = "username") String username) {
            
        order.setDate(LocalDate.now());
        order.setClient(userRepo.findByUsername(username).getClient());
        orderRepository.save(order);
        session.setAttribute("order", new Order());
        return new ModelAndView("redirect:/success");

    }
}
