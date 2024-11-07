package com.financeira.gestao.service;

import com.financeira.gestao.model.UserLoginModel;
import com.financeira.gestao.model.UserModel;
import com.financeira.gestao.repository.UserLoginRepository;
import com.financeira.gestao.repository.UserRepository;
import exception.InvalidFieldException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserLoginRepository userLoginRepository;

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public UserService(UserRepository userRepository, UserLoginRepository userLoginRepository) {
        this.userRepository = userRepository;
        this.userLoginRepository = userLoginRepository;
    }

    @Transactional
    public UserModel createUser(UserModel user, String password) {
        String formattedTel = formatterTel(user.getTel());

        if (!user.getTel().isEmpty() && userRepository.findByTel(formattedTel).isPresent()) {
            throw new InvalidFieldException("Telefone já registrado no sistema, " + user.getTel());
        }

        if (!isValidEmail(user.getEmail())) {
            throw new InvalidFieldException("E-mail inválido");
        }

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new InvalidFieldException("E-mail já registrado no sistema, " + user.getEmail());
        }

        user.setTel(formattedTel);

        UserModel savedUser = userRepository.save(user);

        UserLoginModel userLogin = new UserLoginModel();
        userLogin.setEmail(user.getEmail());
        userLogin.setPassword(passwordEncoder().encode(password));
        userLogin.setUser(savedUser);

        System.out.println(userLogin.getPassword());

        userLoginRepository.save(userLogin);

        return savedUser;
    }

    private String formatterTel(String tel) {
        return tel.replaceAll("[^\\d]", "");
    }

    private boolean isValidEmail(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        boolean isValidEmail = false;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);

        if (matcher.matches()) {
            isValidEmail = true;
        }

        return isValidEmail;
    }
}
