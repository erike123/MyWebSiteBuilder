package com.example.demo.services;

import com.example.demo.domein.entities.Website;
import com.example.demo.domein.models.service.WebSiteServiceModel;
import com.example.demo.repository.WebSiteRepository;
import com.example.demo.validation.WebSiteValidation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebSiteServiceImpl implements WebSiteService {

    private final WebSiteRepository webSiteRepository;
    private final CategoryService categoryService;
    private final WebSiteValidation webSiteValidation;
    private final ModelMapper modelMapper;

    @Autowired
    public WebSiteServiceImpl(WebSiteRepository webSiteRepository, CategoryService categoryService, WebSiteValidation webSiteValidation, ModelMapper modelMapper) {
        this.webSiteRepository = webSiteRepository;
        this.categoryService = categoryService;
        this.webSiteValidation = webSiteValidation;
        this.modelMapper = modelMapper;
    }

    @Override
    public WebSiteServiceModel createSite(WebSiteServiceModel webSiteServiceModel) {
        if (!this.webSiteValidation.IsValid(webSiteServiceModel)){

            throw new IllegalArgumentException();
        }


        Website website= this.modelMapper.map(webSiteServiceModel, Website.class);
        website = this.webSiteRepository.save(website);
        return this.modelMapper.map(website, WebSiteServiceModel.class);
    }

    @Override
    public WebSiteServiceModel findById(String id) {
        return null;
    }
}