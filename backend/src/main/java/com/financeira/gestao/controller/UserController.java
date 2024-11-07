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
//    public ResponseEntity<?> registerUser(@RequestBody UserModel userModel) {
    public ResponseEntity<?> registerUser(@RequestBody Map<String, Object> userMap) {
        try {
            String name = (String) userMap.get("name");
            String familyName = (String) userMap.get("familyName");
            String email = (String) userMap.get("email");
            String tel = (String) userMap.get("tel");
            String password = (String) userMap.get("password");

            UserModel userModel = new UserModel();
            userModel.setName(name);
            userModel.setFamilyName(familyName);
            userModel.setEmail(email);
            userModel.setTel(tel);

            UserModel createUser = userService.createUser(userModel, password);
            return new ResponseEntity<>(createUser, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
