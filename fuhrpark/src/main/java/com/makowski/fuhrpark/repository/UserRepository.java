package com.makowski.fuhrpark.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.makowski.fuhrpark.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{
    Optional<User> findByUsername(String username);
}
