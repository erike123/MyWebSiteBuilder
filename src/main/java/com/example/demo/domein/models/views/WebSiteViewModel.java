package com.example.demo.domein.models.views;

import java.math.BigDecimal;
import java.util.List;

public class WebSiteViewModel {

    private String id;
    private String name;
    private String imageUrl;
    private Integer Likes;
    private BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    private List<String> categories;

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getLikes() {
        return Likes;
    }

    public void setLikes(Integer likes) {
        Likes = likes;
    }
}
