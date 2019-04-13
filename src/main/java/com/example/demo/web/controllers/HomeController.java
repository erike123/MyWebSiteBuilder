package com.example.demo.web.controllers;


import com.example.demo.domein.models.views.CategoryViewModel;
import com.example.demo.services.CategoryService;
import org.hibernate.service.spi.InjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.stream.Collectors;

@RestController
public class HomeController extends BaseController{

   private final CategoryService categoryService;
   private final ModelMapper modelMapper;

   @Autowired
    public HomeController(CategoryService categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/")
    @PreAuthorize("isAnonymous()")
    public ModelAndView Index(){

      return super.view("index");

    }

    @GetMapping("/home")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView home(ModelAndView modelAndView) {
        modelAndView.addObject("categories", this.categoryService.findAllCategories().stream().map(category -> this.modelMapper.map(category, CategoryViewModel.class)).collect(Collectors.toList()));

        return super.view("home", modelAndView);
    }
}
