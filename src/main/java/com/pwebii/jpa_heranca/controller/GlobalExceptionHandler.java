package com.pwebii.jpa_heranca.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)  // Handles all exceptions
    public ModelAndView handleException(Exception ex) {
        ModelAndView mav = new ModelAndView("layout/error-page"); // Thymeleaf error page
        mav.addObject("errorMessage", ex.getMessage()); // Pass error details
        return mav;
    }
    
}
