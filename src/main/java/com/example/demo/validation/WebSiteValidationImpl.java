package com.example.demo.validation;

import com.example.demo.domein.models.service.WebSiteServiceModel;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class WebSiteValidationImpl implements WebSiteValidation {
    @Override
    public boolean IsValid(WebSiteServiceModel webSiteServiceModel) {
        return !webSiteServiceModel.getCategories().isEmpty();
    }
}
