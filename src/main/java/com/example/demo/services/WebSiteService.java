package com.example.demo.services;

import com.example.demo.domein.models.service.WebSiteServiceModel;

import java.util.List;

public interface WebSiteService {

    WebSiteServiceModel createSite(WebSiteServiceModel webSiteServiceModel);

    List<WebSiteServiceModel> findAllWebSites();

    WebSiteServiceModel findById (String id);

}
