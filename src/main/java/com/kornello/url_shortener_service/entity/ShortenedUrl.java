package com.kornello.url_shortener_service.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "shortened_urls")
@Data
public class ShortenedUrl {

    @Id
    @Column(name = "short_url",nullable = false, unique = true)
    private String shortUrl;
    @Column(name = "original_url", nullable = false)
    private String originalUrl;
    @Column(nullable = false)
    private String title;
    @Column(name = "created_at", nullable = false, insertable = false)
    private LocalDateTime createdAt;
}
