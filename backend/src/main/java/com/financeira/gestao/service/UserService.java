package com.financeira.gestao.service;

import com.financeira.gestao.model.UsersModel;
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

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UsersModel createUser(UsersModel user) {
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

        UsersModel savedUser = userRepository.save(user);

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
