package com.web.finalproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "news")
public class NewsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String content;
    private String image_url;
    private int author_id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false,  insertable = false)
    private Date created_at;
    private Date updated_at;
}