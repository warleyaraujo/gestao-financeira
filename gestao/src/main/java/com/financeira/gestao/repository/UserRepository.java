package com.financeira.gestao.repository;

import com.financeira.gestao.model.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<UserModel, Integer> {

}
