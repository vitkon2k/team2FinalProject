package com.web.finalproject.api;

import com.web.finalproject.entity.UserEntity;
import com.web.finalproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
public class UsersApi {
    private final UserService userService;

    @GetMapping
    public List<UserEntity> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Integer id) {
        return userService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public UserEntity createUser(@RequestBody UserEntity user) {
        return userService.create(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Integer id, @RequestBody UserEntity user) {
        return ResponseEntity.ok(userService.update(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
