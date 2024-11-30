package com.financeira.gestao.repository;

import com.financeira.gestao.model.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserModel, String> {
    Optional<UserModel> findByEmail(String email);
    Optional<UserModel> findByTel(String tel);
}
