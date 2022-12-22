package com.capgemini.oap.controller;

import com.capgemini.oap.pojo.UserRequest;
import com.capgemini.oap.pojo.UserResponse;
import com.capgemini.oap.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/user/")
public class UserController {
    private final UserService userService;

    @PostMapping(path = "register/")
    public ResponseEntity<String> registerUser(@RequestBody UserRequest user){
        return new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
    }

    @GetMapping(path = "login/")
    public ResponseEntity<String> loginUser(@RequestParam(value = "value")String value,@RequestParam(value = "password")String password) {
        return new ResponseEntity<>(userService.loginUser(value,password), HttpStatus.OK);
    }

    @GetMapping(path = "get-by-username/")
    public ResponseEntity<UserResponse> getUserByUserName(@RequestParam(value = "username")String username){
        return new ResponseEntity<>(userService.getUserByUserName(username), HttpStatus.OK);
    }

    @GetMapping(path = "get-by-id/")
    public ResponseEntity<UserResponse> getUserById(@RequestParam(value = "id")int id){
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }
}