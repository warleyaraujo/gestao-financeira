package com.financeira.gestao.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "user")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, unique = true, name = "user_id")
    private String userId;

    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private String familyName;

    @NotNull
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = true)
    private String tel;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserLoginModel userLogin;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public @NotNull String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public @NotNull String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(@NotNull String familyName) {
        this.familyName = familyName;
    }

    public @NotNull String getEmail() {
        return email;
    }

    public void setEmail(@NotNull String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public UserLoginModel getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(UserLoginModel userLogin) {
        this.userLogin = userLogin;
    }
}
