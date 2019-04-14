package com.example.demo.web.controllers;

import com.example.demo.domein.entities.Company;
import com.example.demo.domein.models.bindings.CategoryAddBindingModel;
import com.example.demo.domein.models.service.CategoryServiceModel;
import com.example.demo.domein.models.views.CategoryViewModel;
import com.example.demo.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/categories")
public class CategoryController extends BaseController {

    private final CategoryService categoryService;
    private final ModelMapper modelMapper;
    private final Path rootPath = Paths.get("/tmp");

    @Autowired
    public CategoryController(CategoryService categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addCategory(ModelAndView modelAndView,@ModelAttribute(name = "bindingModel") CategoryAddBindingModel model) {
        modelAndView.addObject("bindingModel",model);
        return super.view("add-category",modelAndView);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addCategoryConfirm(@Valid@ModelAttribute CategoryAddBindingModel model, BindingResult bindingResult,ModelAndView modelAndView) {

        if (bindingResult.hasErrors()){
            modelAndView.addObject("bindingModel",model);
            return super.view("add-category",modelAndView);

        }

        this.categoryService.addCategory(this.modelMapper.map(model, CategoryServiceModel.class));

        return super.redirect("/categories/all");
    }
    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView allCategories(ModelAndView modelAndView) {
        modelAndView.addObject("categories",
                this.categoryService.findAllCategories()
                        .stream()
                        .map(c -> this.modelMapper.map(c, CategoryViewModel.class))
                        .collect(Collectors.toList())
        );

        return super.view("all-categories", modelAndView);
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView editCategory(@PathVariable String id, ModelAndView modelAndView) {
        modelAndView.addObject("model",
                this.modelMapper.map(this.categoryService.findCategoryById(id), CategoryViewModel.class)
        );

        return super.view("category/edit-category", modelAndView);
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView editCategoryConfirm(@PathVariable String id, @ModelAttribute CategoryAddBindingModel model) {
        this.categoryService.editCategory(id, this.modelMapper.map(model, CategoryServiceModel.class));

        return super.redirect("/categories/all");
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView deleteCategory(@PathVariable String id, ModelAndView modelAndView) {

        return super.redirect("categories/delete/{id}");
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView deleteCategoryConfirm(@PathVariable String id) {
        this.categoryService.deleteCategory(id);

        return super.redirect("/categories/all");
    }

    @GetMapping("/fetch")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @ResponseBody
    public List<CategoryViewModel> fetchCategories() {
        return this.categoryService.findAllCategories()
                .stream()
                .map(c -> this.modelMapper.map(c, CategoryViewModel.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/fetch/companies")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @ResponseBody
    public List<Company> fetchCompanies() {
        return Arrays.asList(Company.values());

    }
    @GetMapping("/fetch/IndexPage")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @ResponseBody
    public List<String> fetchIndexPages() throws IOException {
        File f = new File("C:\\Users\\email\\Desktop\\Project-Product-Shop-master\\demo\\src\\main\\resources\\templates\\IndexPages");
        File[] list = f.listFiles();
        List<String> response = new ArrayList<>();
        for (File file : list) {
            String [] array = file.toString().split("\\\\");
            String page = array[array.length-1];
            response.add(page);
        }

        return response;
    }
    @GetMapping("/fetch/LoginPage")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @ResponseBody
    public List<String> fetchLoginPages() throws IOException {
        File f = new File("C:\\Users\\email\\Desktop\\Project-Product-Shop-master\\demo\\src\\main\\resources\\templates\\LoginPages");
        File[] list = f.listFiles();
        List<String> response = new ArrayList<>();
        for (File file : list) {
            String [] array = file.toString().split("\\\\");
            String page = array[array.length-1];
            response.add(page);
        }

        return response;
    }
    @GetMapping("/fetch/RegisterPage")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @ResponseBody
    public List<String> fetchRegisterPages() throws IOException {
        File f = new File("C:\\Users\\email\\Desktop\\Project-Product-Shop-master\\demo\\src\\main\\resources\\templates\\RegisterPages");
        File[] list = f.listFiles();
        List<String> response = new ArrayList<>();
        for (File file : list) {
            String [] array = file.toString().split("\\\\");
            String page = array[array.length-1];
            response.add(page);
        }

        return response;
    }
}
