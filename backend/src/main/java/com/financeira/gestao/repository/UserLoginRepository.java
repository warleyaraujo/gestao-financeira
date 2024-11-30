package com.financeira.gestao.repository;

import com.financeira.gestao.model.UserLoginModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginRepository extends CrudRepository<UserLoginModel, String> {
}
