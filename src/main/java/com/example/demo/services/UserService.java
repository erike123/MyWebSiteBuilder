package com.example.demo.services;


import com.example.demo.domein.models.service.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    UserServiceModel registerUser(UserServiceModel userServiceModel);

    UserServiceModel findUserByUserName(String username);

    UserServiceModel editUserProfile(UserServiceModel userServiceModel);

    List<UserServiceModel> findAllUsers();

    void setUserRole(String id, String role);
}
