package com.kornello.url_shortener_service.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kornello.url_shortener_service.entity.ShortenedUrl;
import com.kornello.url_shortener_service.repo.ShortenerRepo;
import com.kornello.url_shortener_service.service.ShortenerService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.apache.commons.lang3.RandomStringUtils.random;

@Service
@RequiredArgsConstructor
public class ShortenerServiceImpl implements ShortenerService {

    private final ShortenerRepo repo;
    private final ObjectMapper mapper;

    @Override
    @SneakyThrows
    @Retryable
    @Transactional
    public String shortUrl(String json) {
        JsonNode requestBody = mapper.readTree(json);
        String originalUrl = requestBody.findValue("url").asText();
        String shortUrl = generateShortUrl();
        String title = requestBody.findValue("title").asText();

        ShortenedUrl shortenedUrl = new ShortenedUrl();
        shortenedUrl.setOriginalUrl(originalUrl);
        shortenedUrl.setShortUrl(shortUrl);
        shortenedUrl.setTitle(title);

        ShortenedUrl persistShortenedUrl = repo.save(shortenedUrl);
        return persistShortenedUrl.getShortUrl();
    }

    @Override
    public String getOriginalUrlFromShortUrl(String shortUrl) {
        return repo.findOriginalUrlByShortUrl(shortUrl);
    }

    private String generateShortUrl() {
        while (true) {
            var shortUrl = random(6, true, true);
            if (!repo.existsById(shortUrl)) {
                return shortUrl;
            }
        }
    }
}
