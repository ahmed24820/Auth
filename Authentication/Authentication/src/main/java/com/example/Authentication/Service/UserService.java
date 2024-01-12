package com.example.Authentication.Service;

import com.example.Authentication.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User saveuser(User user);


    //void addroletouser(String username,String name);

    Optional<User> getuser(String username);

    List<User> Getallusers();
}