package com.example.demo.web.controllers;

import com.example.demo.domein.models.bindings.CategoryAddBindingModel;
import com.example.demo.domein.models.bindings.UserRegisterBindingModel;
import com.example.demo.domein.models.service.CategoryServiceModel;
import com.example.demo.services.CategoryService;
import org.dom4j.rule.Mode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategoryController extends BaseController {

    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryController(CategoryService categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addCategory(ModelAndView modelAndView, @ModelAttribute(name = "bindingModel") CategoryAddBindingModel model) {
        modelAndView.addObject("bindingModel",model);
        return super.view("category/add-category",modelAndView);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addCategoryConfirm(@ModelAttribute CategoryAddBindingModel model) {
        this.categoryService.addCategory(this.modelMapper.map(model, CategoryServiceModel.class));

        return super.redirect("/categories/all");
    }
}
