package com.financeira.gestao.repository;

import com.financeira.gestao.model.UsersModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UsersModel, String> {
    Optional<UsersModel> findByEmail(String email);
    Optional<UsersModel> findByTel(String tel);
}
