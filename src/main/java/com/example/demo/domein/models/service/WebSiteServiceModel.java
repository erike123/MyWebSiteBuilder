package com.example.demo.domein.models.service;

import com.example.demo.domein.entities.Category;
import com.example.demo.domein.entities.Company;

import java.math.BigDecimal;
import java.util.List;

public class WebSiteServiceModel {

    private String Name;
    private String Description;
    private BigDecimal price;
    private List<CategoryServiceModel> categories;
    private Integer Likes;
    private String imageUrl;

    public WebSiteServiceModel() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<CategoryServiceModel> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryServiceModel> categories) {
        this.categories = categories;
    }

    public Integer getLikes() {
        return Likes;
    }

    public void setLikes(Integer likes) {
        Likes = likes;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    private String company;
}
