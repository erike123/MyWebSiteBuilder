package com.example.demo.domein.entities;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

public class Production extends BaseEntity {

    private Website website;
    private User user;

    @ManyToOne(targetEntity = Website.class)
    public Website getWebsite() {
        return website;
    }

    public void setWebsite(Website website) {
        this.website = website;
    }

    @ManyToOne(targetEntity = User.class)
    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }
}
