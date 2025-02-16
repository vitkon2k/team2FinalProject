package com.web.finalproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

@Data
@Entity
@Table(name = "Users")
public class UserEntity {
    @Id
    @UuidGenerator
    @Column(name = "UserId")
    private String id;
    @Column(name = "Username")
    private String username;
    @Column(name = "Password")
    private String password;
    @Column(name = "Roles")
    private String roles;
    @Column(name = "Status")
    private String status;
}
