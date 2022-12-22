package com.capgemini.oap.service;

import com.capgemini.oap.pojo.UserRequest;
import com.capgemini.oap.pojo.UserResponse;

public interface UserService {

    String registerUser(UserRequest user);
    String loginUser(String value,String password);

    UserResponse getUserByUserName(String username);

    UserResponse getUserById(int id);
}
