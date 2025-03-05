package com.web.finalproject.service;

import com.web.finalproject.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    List<UserEntity> getAll();
    Optional<UserEntity> getById(Integer id);
    UserEntity create(UserEntity user);
    UserEntity update(Integer id, UserEntity user);
    void delete(Integer id);
}
