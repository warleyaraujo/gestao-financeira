package com.financeira.gestao.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "user_login")
public class UserLoginModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, unique = true, name = "login_id")
    private String loginId;

    @NotNull
    @Column(nullable = false, unique = false)
    private String email;

    @NotNull
    @Column(nullable = false, unique = false)
    private String password;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false, unique = true)
    private UserModel user;

    public @NotNull String getLoginId() {
        return loginId;
    }

    public void setLoginId(@NotNull String loginId) {
        this.loginId = loginId;
    }

    public @NotNull String getEmail() {
        return email;
    }

    public void setEmail(@NotNull String email) {
        this.email = email;
    }

    public @NotNull String getPassword() {
        return password;
    }

    public void setPassword(@NotNull String password) {
        this.password = password;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
