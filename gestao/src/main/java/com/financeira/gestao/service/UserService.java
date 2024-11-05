package com.financeira.gestao.service;

import com.financeira.gestao.model.UserModel;
import com.financeira.gestao.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserModel createUser(UserModel user) {
        return userRepository.save(user);
    }
}
