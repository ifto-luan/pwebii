package com.pwebii.jpa_heranca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("success")
public class SucessController {
    
    @GetMapping
    public ModelAndView getMethodName() {
        return new ModelAndView("/cart/sale-success-page");
    }
    

}
