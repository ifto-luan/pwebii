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

import com.pwebii.jpa_heranca.model.entity.Sale;
import com.pwebii.jpa_heranca.model.repository.SaleRepository;

@Controller
@RequestMapping("admin/sale")
public class SaleController {

    @Autowired
    SaleRepository repo;

    @GetMapping
    public ModelAndView listSales(ModelMap model) {
        model.addAttribute("sales", repo.findAll());
        return new ModelAndView("admin/sale/sale-list", model);
    }

    @GetMapping("/{id}")
    public ModelAndView getSale(@PathVariable Long id, ModelMap model) {
        model.addAttribute("sale", repo.findById(id).orElseThrow(() -> new NoSuchElementException("Sell not found")));
        return new ModelAndView("admin/sale/sale", model);
    }

    @GetMapping("/search")
    public ModelAndView findByDate(@RequestParam(required = false) String date, ModelMap model) {

        List<Sale> sales;

        if (date != null) {

            if (!date.isEmpty()) {

                sales = repo.findByDate(LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")));

            } else {

                return new ModelAndView("redirect:/admin/sale");

            }

        } else {

            sales = repo.findAll();

        }

        model.addAttribute("sales", sales);
        model.addAttribute("searchDate", date);

        return new ModelAndView("admin/sale/sale-list");

    }

    @GetMapping("/client_sales/{id}")
    public ModelAndView listSalesByClientId(@PathVariable Long id, ModelMap model) {

        List<Sale> sales;

        if (id != null) {

            sales = repo.findByClientId(id);

            if (!sales.isEmpty()) {

                model.addAttribute("clientName", sales.get(0).getClient().getName());
                model.addAttribute("sales", sales);
                return new ModelAndView("admin/sale/sales-per-client");

            }

        }

        return new ModelAndView("redirect:/admin/person");

    }
}
