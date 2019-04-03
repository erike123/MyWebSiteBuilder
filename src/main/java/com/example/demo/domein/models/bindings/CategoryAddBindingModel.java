package com.example.demo.domein.models.bindings;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CategoryAddBindingModel {

    @NotNull
    @Size(min = 2,max =10,message = "category must be between 2 and 10 characters")
    private String name;

    public CategoryAddBindingModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
