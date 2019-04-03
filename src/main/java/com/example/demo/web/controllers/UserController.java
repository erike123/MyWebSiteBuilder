package com.example.demo.web.controllers;

import com.example.demo.domein.models.bindings.UserRegisterBindingModel;
import com.example.demo.domein.models.service.UserServiceModel;
import com.example.demo.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.Binding;
import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView register(ModelAndView modelAndView,@ModelAttribute(name = "bindingModel") UserRegisterBindingModel model) {

        modelAndView.addObject("bindingModel",model);
        return super.view("register",modelAndView);
    }

    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView registerConfirm(@Valid @ModelAttribute(name = "bindingModel") UserRegisterBindingModel model , BindingResult bindingResult,ModelAndView modelAndView) {

        if (bindingResult.hasErrors()){
            modelAndView.addObject("bindingModel",model);
            return super.view("register",modelAndView);

        }

        if (!model.getPassword().equals(model.getConfirmPassword())) {
            return super.view("register");
        }

        this.userService.registerUser(this.modelMapper.map(model, UserServiceModel.class));

        return super.redirect("/login");
    }

    @GetMapping("/login")
    @PreAuthorize("isAnonymous()")
    public ModelAndView login() {
        return super.view("login");
    }

    @PostMapping("/login")
    @PreAuthorize("isAnonymous()")
    public ModelAndView loginPost(){
        return super.view("home");
    }
}
