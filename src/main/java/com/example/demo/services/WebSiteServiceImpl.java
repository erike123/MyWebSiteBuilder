package com.example.demo.services;

import com.example.demo.domein.entities.Category;
import com.example.demo.domein.entities.Website;
import com.example.demo.domein.models.bindings.WebSiteAddBindingModel;
import com.example.demo.domein.models.service.WebSiteServiceModel;
import com.example.demo.domein.models.views.WebSiteViewModel;
import com.example.demo.repository.WebSiteRepository;
import com.example.demo.validation.WebSiteValidation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WebSiteServiceImpl implements WebSiteService {

    private final WebSiteRepository webSiteRepository;
    private final CategoryService categoryService;
    private final WebSiteValidation webSiteValidation;
    private final ModelMapper modelMapper;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public WebSiteServiceImpl(WebSiteRepository webSiteRepository, CategoryService categoryService, WebSiteValidation webSiteValidation, ModelMapper modelMapper, CloudinaryService cloudinaryService) {
        this.webSiteRepository = webSiteRepository;
        this.categoryService = categoryService;
        this.webSiteValidation = webSiteValidation;
        this.modelMapper = modelMapper;
        this.cloudinaryService = cloudinaryService;
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
    public List<WebSiteServiceModel> findAllWebSites() {
        return this.webSiteRepository.findAll()
                .stream()
                .map(p -> this.modelMapper.map(p, WebSiteServiceModel.class))
                .collect(Collectors.toList());
    }


    @Override
    public WebSiteServiceModel findById(String id){
        Website website = this.webSiteRepository.findById(id).orElse(null);
        return this.modelMapper.map(website,WebSiteServiceModel.class);
    }

    @Override
    public Boolean deleteWebSite(String id) {
      Website website=this.webSiteRepository.findById(id).orElse(null);

      if (website!=null){

          this.webSiteRepository.delete(website);
      }
      else{
          return false;
      }

      return true;
    }

    @Override
    public WebSiteServiceModel editWebsite(String id, WebSiteAddBindingModel webSiteAddBindingModel) throws IOException {
       Website website = this.webSiteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());



        website.setName(webSiteAddBindingModel.getName());

        website.setPrice(webSiteAddBindingModel.getPrice());
        website.setCategories(
                this.categoryService.findAllCategories()
                        .stream()
                        .filter(c -> webSiteAddBindingModel.getCategories().contains(c.getId()))
                        .map(c -> this.modelMapper.map(c, Category.class))
                        .collect(Collectors.toList())
        );


        website.setImageUrl(
                this.cloudinaryService.uploadImage(webSiteAddBindingModel.getImage())
        );

        return this.modelMapper.map(this.webSiteRepository.saveAndFlush(website), WebSiteServiceModel.class);
    }

    @Override
    public List<WebSiteViewModel> findAllByCategory(String category) {

        List<WebSiteViewModel> MyList = this.webSiteRepository.findAll().stream()
                .map(web -> {
                   WebSiteViewModel webSiteViewModel = this.modelMapper.map(web, WebSiteViewModel.class);
                   webSiteViewModel.getCategories().clear();
                   web.getCategories().forEach(category1 -> webSiteViewModel.getCategories().add(category1.getName()));
                   return webSiteViewModel;
                })
                .filter(web ->
                        web.getCategories().contains(category))

                .collect(Collectors.toList());


        return MyList;

    }
}
