package com.example.demo.domein.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "website")
public class Website extends BaseEntity {

    private String Name;
    private String LoginPage;
    private String IndexPage;
    private String RegisterPage;
    private BigDecimal price;
    private List<Category> categories;
    private Integer Likes;
    private String imageUrl;
    private Company company;

    @Enumerated
    @Column(name = "company")
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Column(name = "name")
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Column(name = "login_page")
    public String getLoginPage() {
        return LoginPage;
    }

    public void setLoginPage(String loginPage) {
        LoginPage = loginPage;
    }

    @Column(name = "index_page")
    public String getIndexPage() {
        return IndexPage;
    }

    public void setIndexPage(String indexPage) {
        IndexPage = indexPage;
    }

    @Column(name = "register_page")
    public String getRegisterPage() {
        return RegisterPage;
    }

    public void setRegisterPage(String registerPage) {
        RegisterPage = registerPage;
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


    @Column(name = "image")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Column(name = "likes")
    public Integer getLikes() {
        return Likes;
    }

    public void setLikes(Integer likes) {
        Likes = 0;
    }
}
