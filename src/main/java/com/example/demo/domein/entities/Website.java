package com.example.demo.domein.entities;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.math.BigDecimal;
import java.util.List;

public class Website {

    private String Name;
    private String Description;
    private BigDecimal price;

    @Column(name = "name")
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Column(name = "description")
    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @ManyToMany(targetEntity = Category.class)
    @JoinTable(
            name = "website_categories",
            joinColumns = @JoinColumn(
                    name = "website_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "category_id",
                    referencedColumnName = "id"
            )
    )
    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }


    @Column(name = "numberOfOrders")
    public Integer getNumberOfOrders() {
        return NumberOfOrders;
    }

    public void setNumberOfOrders(Integer numberOfOrders) {
        NumberOfOrders = numberOfOrders;
    }

    private String imageUrl;

    @Column(name = "image")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    private List<Category> categories;
    private Integer NumberOfOrders;
}
