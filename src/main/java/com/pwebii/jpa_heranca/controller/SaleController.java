package com.pwebii.jpa_heranca.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pwebii.jpa_heranca.model.repository.SaleRepository;

@Controller
@RequestMapping("sale")
public class SaleController {

    @Autowired
    SaleRepository saleRepository;

    @GetMapping
    public ModelAndView listSales(ModelMap model) {
        model.addAttribute("sales", saleRepository.findAll());
        return new ModelAndView("sale/sale-list", model);
    }

    @GetMapping("/{id}")
    public ModelAndView getSale(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("sale", saleRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Sell not found")));
        return new ModelAndView("sale/sale", model);
    }

}
