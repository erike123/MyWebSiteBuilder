package com.example.demo.web.controllers;


import org.dom4j.rule.Mode;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController extends BaseController{


    @GetMapping("/")
    @PreAuthorize("isAnonymous()")
    public ModelAndView Index(){

      return super.view("index");

    }

    @GetMapping("/home")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView Home(){

        return super.view("home");

    }

}
