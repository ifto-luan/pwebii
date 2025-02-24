package com.pwebii.jpa_heranca.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

import com.pwebii.jpa_heranca.model.entity.Order;
import com.pwebii.jpa_heranca.model.repository.OrderRepository;

import jakarta.transaction.Transactional;

@Controller
@RequestMapping("admin/order")
@Transactional
public class OrderController {

    @Autowired
    OrderRepository repo;

    @GetMapping
    public ModelAndView listOrders(ModelMap model) {
        model.addAttribute("orders", repo.findAllWithCustomer());
        return new ModelAndView("admin/order/order-list", model);
    }

    @GetMapping("/{id}")
    public ModelAndView getOrder(@PathVariable Long id, ModelMap model) {
        model.addAttribute("order", repo.findById(id).orElseThrow(() -> new NoSuchElementException("Order not found")));
        return new ModelAndView("admin/order/order", model);
    }

    @GetMapping("/search")
    public ModelAndView findByDate(@RequestParam(required = false) String date, ModelMap model) {

        List<Order> orders;

        if (date != null) {

            if (!date.isEmpty()) {

                orders = repo.findByDate(LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")));

            } else {

                return new ModelAndView("redirect:/admin/order");

            }

        } else {

            orders = repo.findAll();

        }

        model.addAttribute("orders", orders);
        model.addAttribute("searchDate", date);

        return new ModelAndView("admin/order/order-list");

    }

    @GetMapping("/client_orders/{id}")
    public ModelAndView listOrdersByClientId(@PathVariable Long id, ModelMap model) {

        List<Order> orders;

        if (id != null) {

            orders = repo.findByClientId(id);

            if (!orders.isEmpty()) {

                model.addAttribute("clientName", orders.get(0).getClient().getName());
                model.addAttribute("orders", orders);
                return new ModelAndView("admin/order/orders-per-client");

            }

        }

        return new ModelAndView("redirect:/admin/person");

    }
}
