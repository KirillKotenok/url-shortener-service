package com.kornello.url_shortener_service.service;

public interface ShortenerService {

    String shortUrl(String json);

    String getOriginalUrlFromShortUrl(String shortUrl);
}
