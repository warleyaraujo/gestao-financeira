package com.financeira.gestao.controller;

import com.financeira.gestao.model.UserModel;
import com.financeira.gestao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public Map<String, String> helloWorld() {
        Map <String, String> response = new HashMap<>();
        response.put("say", "Hello World!");
        return response;
    }

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
