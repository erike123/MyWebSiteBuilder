package com.example.demo.domein.models.bindings;

import com.example.demo.domein.entities.Company;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

public class WebSiteAddBindingModel {


    private String name;
    private String LoginPage;
    private String IndexPage;
    private String RegisterPage;
    private String company;
    private BigDecimal price;
    private MultipartFile image;
    private List<String> categories;

    public WebSiteAddBindingModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }


}
