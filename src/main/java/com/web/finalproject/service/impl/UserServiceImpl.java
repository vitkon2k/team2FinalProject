package com.web.finalproject.service.impl;

import com.web.finalproject.entity.UserEntity;
import com.web.finalproject.model.UserAdapter;
import com.web.finalproject.repository.UserRepository;
import com.web.finalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity != null) {
            return new UserAdapter(userEntity);
        }
        throw new UsernameNotFoundException("Username " + username + " not found");
    }
}
