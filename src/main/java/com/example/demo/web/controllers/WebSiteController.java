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
@RequestMapping("/products")
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
    public ModelAndView addProduct() {
        return super.view("add-website");
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addProductConfirm(@ModelAttribute WebSiteAddBindingModel model) throws IOException {
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

        return super.redirect("/products/all");
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView allProducts(ModelAndView modelAndView) {
        modelAndView.addObject("websites", this.webSiteService.findAllWebSites()
                .stream()
                .map(p -> this.modelMapper.map(p, WebSiteViewModel.class))
                .collect(Collectors.toList()));

        return super.view("product/all-products", modelAndView);
    }

//    @GetMapping("/details/{id}")
//    @PreAuthorize("isAuthenticated()")
//    public ModelAndView detailsProduct(@PathVariable String id, ModelAndView modelAndView) {
//        modelAndView.addObject("product", this.modelMapper.map(this.productService.findProductById(id), ProductDetailsViewModel.class));
//
//        return super.view("product/details", modelAndView);
//    }
//
//    @GetMapping("/edit/{id}")
//    @PreAuthorize("hasRole('ROLE_MODERATOR')")
//    public ModelAndView editProduct(@PathVariable String id, ModelAndView modelAndView) {
//        WebSiteServiceModel WebSiteServiceModel = this.productService.findProductById(id);
//        WebSiteAddBindingModel model = this.modelMapper.map(WebSiteServiceModel, WebSiteAddBindingModel.class);
//        model.setCategories(WebSiteServiceModel.getCategories().stream().map(c -> c.getName()).collect(Collectors.toList()));
//
//        modelAndView.addObject("product", model);
//        modelAndView.addObject("productId", id);
//
//        return super.view("product/edit-product", modelAndView);
//    }
//
//    @PostMapping("/edit/{id}")
//    @PreAuthorize("hasRole('ROLE_MODERATOR')")
//    public ModelAndView editProductConfirm(@PathVariable String id, @ModelAttribute WebSiteAddBindingModel model) {
//        this.productService.editProduct(id, this.modelMapper.map(model, WebSiteServiceModel.class));
//
//        return super.redirect("/products/details/" + id);
//    }
//
//    @GetMapping("/delete/{id}")
//    @PreAuthorize("hasRole('ROLE_MODERATOR')")
//    public ModelAndView deleteProduct(@PathVariable String id, ModelAndView modelAndView) {
//        WebSiteServiceModel WebSiteServiceModel = this.productService.findProductById(id);
//        WebSiteAddBindingModel model = this.modelMapper.map(WebSiteServiceModel, WebSiteAddBindingModel.class);
//        model.setCategories(WebSiteServiceModel.getCategories().stream().map(c -> c.getName()).collect(Collectors.toList()));
//
//        modelAndView.addObject("product", model);
//        modelAndView.addObject("productId", id);
//
//        return super.view("product/delete-product", modelAndView);
//    }
//
//    @PostMapping("/delete/{id}")
//    @PreAuthorize("hasRole('ROLE_MODERATOR')")
//    public ModelAndView deleteProductConfirm(@PathVariable String id) {
//        this.productService.deleteProduct(id);
//
//        return super.redirect("/products/all");
//    }
//
//    @GetMapping("/fetch/{category}")
//    @ResponseBody
//    public List<ProductAllViewModel> fetchByCategory(@PathVariable String category) {
//        if(category.equals("all")) {
//            return this.productService.findAllProducts()
//                    .stream()
//                    .map(product -> this.modelMapper.map(product, ProductAllViewModel.class))
//                    .collect(Collectors.toList());
//        }
//
//        return this.productService.findAllByCategory(category)
//                .stream()
//                .map(product -> this.modelMapper.map(product, ProductAllViewModel.class))
//                .collect(Collectors.toList());
//    }

}
