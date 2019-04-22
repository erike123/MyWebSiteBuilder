package com.example.demo.services;

import com.example.demo.domein.models.bindings.WebSiteAddBindingModel;
import com.example.demo.domein.models.service.WebSiteServiceModel;
import com.example.demo.domein.models.views.WebSiteViewModel;

import java.io.IOException;
import java.util.List;

public interface WebSiteService {

    WebSiteServiceModel createSite(WebSiteServiceModel webSiteServiceModel);

    List<WebSiteServiceModel> findAllWebSites();

    WebSiteServiceModel findById (String id);

    Boolean deleteWebSite(String id);

    WebSiteServiceModel editWebsite(String id , WebSiteAddBindingModel webSiteAddBindingModel) throws IOException;

}
