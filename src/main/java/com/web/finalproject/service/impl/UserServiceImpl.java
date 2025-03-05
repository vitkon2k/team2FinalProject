package com.web.finalproject.service.impl;

import com.web.finalproject.entity.UserEntity;
import com.web.finalproject.model.UserAdapter;
import com.web.finalproject.repository.UserRepository;
import com.web.finalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> getById(Integer id) {
        return userRepository.findById(Long.valueOf(id));
    }

    @Override
    public UserEntity create(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserEntity update(Integer id, UserEntity user) {
        return userRepository.findById(Long.valueOf(id))
                .map(existingUser -> {
                    existingUser.setEmail(user.getEmail());
                    existingUser.setPhone(user.getPhone());
                    existingUser.setAddress(user.getAddress());
                    existingUser.setRole(user.getRole());

                    if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                        existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
                    }

                    return userRepository.save(existingUser);
                }).orElseThrow(() -> new UsernameNotFoundException("Người dùng không tồn tại"));
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(Long.valueOf(id));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity != null) {
            return new UserAdapter(userEntity);
        }
        throw new UsernameNotFoundException("Không tìm thấy người dùng với email: " + email);
    }


}
