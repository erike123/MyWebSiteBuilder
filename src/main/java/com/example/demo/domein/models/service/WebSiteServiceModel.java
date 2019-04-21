package com.example.demo.domein.models.service;

import com.example.demo.domein.entities.Category;
import com.example.demo.domein.entities.Company;

import java.math.BigDecimal;
import java.util.List;

public class WebSiteServiceModel {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String Name;
    private String LoginPage;
    private String IndexPage;
    private String RegisterPage;
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

    public String getLoginPage() {
        return LoginPage;
    }

    public void setLoginPage(String loginPage) {
        LoginPage = loginPage;
    }

    public String getIndexPage() {
        return IndexPage;
    }

    public void setIndexPage(String indexPage) {
        IndexPage = indexPage;
    }

    public String getRegisterPage() {
        return RegisterPage;
    }

    public void setRegisterPage(String registerPage) {
        RegisterPage = registerPage;
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
