package com.kornello.url_shortener_service.repo;

import com.kornello.url_shortener_service.entity.ShortenedUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortenerRepo extends JpaRepository<ShortenedUrl, String> {
    @Query("select s.originalUrl from ShortenedUrl s where s.shortUrl = ?1")
    String findOriginalUrlByShortUrl(String shortUrl);
}
