package com.example.demo.web.controllers;

import com.example.demo.domein.models.bindings.WebSiteAddBindingModel;
import com.example.demo.domein.models.service.WebSiteServiceModel;
import com.example.demo.domein.models.views.WebSiteViewModel;
import com.example.demo.services.CategoryService;
import com.example.demo.services.CloudinaryService;
import com.example.demo.services.WebSiteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/websites")
public class WebSiteController extends BaseController {

    private final WebSiteService webSiteService;
    private final CloudinaryService cloudinaryService;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @Autowired
    public WebSiteController(WebSiteService webSiteService, CloudinaryService cloudinaryService, CategoryService categoryService, ModelMapper modelMapper) {
        this.webSiteService = webSiteService;
        this.cloudinaryService = cloudinaryService;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addWebsite() {
        return super.view("add-website");
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addWebbSitePost(@ModelAttribute WebSiteAddBindingModel model) throws IOException {
        WebSiteServiceModel webSiteServiceModel = this.modelMapper.map(model, WebSiteServiceModel.class);
        webSiteServiceModel.setCategories(
                this.categoryService.findAllCategories()
                        .stream()
                        .filter(c -> model.getCategories().contains(c.getId()))
                        .collect(Collectors.toList())
        );
        webSiteServiceModel.setImageUrl(
                this.cloudinaryService.uploadImage(model.getImage())
        );

        this.webSiteService.createSite(webSiteServiceModel);

        return super.redirect("/websites/all");
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView allProducts(ModelAndView modelAndView) {
        modelAndView.addObject("websites", this.webSiteService.findAllWebSites());

        return super.view("all-websites", modelAndView);
    }

    @GetMapping("/details/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView detailsProduct(@PathVariable String id, ModelAndView modelAndView) {
        WebSiteViewModel model = this.modelMapper.map(this.webSiteService.findById(id), WebSiteViewModel.class);
        model.setCategories(this.webSiteService.findById(id).getCategories().stream()
        .map(c->c.getName()).collect(Collectors.toList()));
        modelAndView.addObject("website",model);

        return super.view("website-details", modelAndView);
    }
//
    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView editProduct(@PathVariable String id, ModelAndView modelAndView) {
        WebSiteServiceModel webSiteServiceModel = this.webSiteService.findById(id);
        WebSiteViewModel model = this.modelMapper.map(webSiteServiceModel, WebSiteViewModel.class);
        model.setCategories(webSiteServiceModel.getCategories().stream().map(c -> c.getName()).collect(Collectors.toList()));

        modelAndView.addObject("website", model);
        modelAndView.addObject("websiteId", id);

        return super.view("edit-website", modelAndView);
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView editProductConfirm(@PathVariable String id, @ModelAttribute WebSiteAddBindingModel model) throws IOException {
        this.webSiteService.editWebsite(id, model);


        return super.redirect("/websites/details/" + id);
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView deleteProductConfirm(@PathVariable String id) {
        this.webSiteService.deleteWebSite(id);

        return super.redirect("/websites/all");
    }

    @GetMapping("/fetch/{category}")
    @ResponseBody
    public List<WebSiteViewModel> fetchByCategory(@PathVariable String category) {
        if(category.equals("all")) {
            return this.webSiteService.findAllWebSites()
                    .stream()
                    .map(website -> this.modelMapper.map(website, WebSiteViewModel.class))
                    .collect(Collectors.toList());
        }

        return this.webSiteService.findAllByCategory(category);

    }

}
