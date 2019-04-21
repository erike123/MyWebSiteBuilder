package com.example.demo.domein.models.views;

import java.math.BigDecimal;
import java.util.List;

public class WebSiteViewModel {

    private String id;
    private String name;

    private List<String> categories;

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
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

    private BigDecimal price;
    private String LoginPage;
    private String IndexPage;
    private String RegisterPage;
    private String imageUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    private Integer likes;

}
