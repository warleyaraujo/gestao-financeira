package com.financeira.gestao.controller;

import com.financeira.gestao.model.UserModel;
import com.financeira.gestao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register-user")
    public ResponseEntity<?> registerUser(@RequestBody UserModel userModel) {

        try {
            UserModel createUser = userService.createUser(userModel);
            return new ResponseEntity<>(createUser, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
