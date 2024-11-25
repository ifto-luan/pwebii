package com.pwebii.jpa_heranca.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringConfiguration implements WebMvcConfigurer {

    @Override
    public void addViewControllers(@SuppressWarnings("null") ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/home").setViewName("index");

    }

}