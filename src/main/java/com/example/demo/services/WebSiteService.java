package com.example.demo.services;

import com.example.demo.domein.models.service.WebSiteServiceModel;

public interface WebSiteService {

    WebSiteServiceModel createSite(WebSiteServiceModel webSiteServiceModel);

    WebSiteServiceModel findById (String id);

}
