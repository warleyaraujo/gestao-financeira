package com.financeira.gestao.controller;

import com.financeira.gestao.model.UsersModel;
import com.financeira.gestao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public String responseHello() {
        return "Hello world";
    }

    @PostMapping("/register-user")
    public ResponseEntity<?> registerUser(@RequestBody Map<String, Object> userMap) {
        try {
            String name = (String) userMap.get("name");
            String familyName = (String) userMap.get("familyName");
            String email = (String) userMap.get("email");
            String tel = (String) userMap.get("tel");
            String password = (String) userMap.get("password");

            UsersModel usersModel = new UsersModel();
            usersModel.setName(name);
            usersModel.setFamilyName(familyName);
            usersModel.setEmail(email);
            usersModel.setTel(tel);
            usersModel.setPassword(password);

            UsersModel createUser = userService.createUser(usersModel);
            return new ResponseEntity<>(createUser, HttpStatus.CREATED);
        } catch (Exception e) {
            new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            throw new RuntimeException(e);
        }
    }
}
