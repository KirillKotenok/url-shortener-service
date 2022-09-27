package com.kornello.url_shortener_service.controller;

import com.kornello.url_shortener_service.service.ShortenerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/shortnera")
@RequiredArgsConstructor
public class ShortnerController {

    private final ShortenerService ss;

    @PostMapping("/short")
    public ResponseEntity<?> shortProvidedUrl(@RequestBody String json) {
        var shortUrl = ss.shortUrl(json);
        return ResponseEntity
                .created(URI.create(shortUrl))
                .build();
    }

    @GetMapping("/short/{shortnerId}")
    public ResponseEntity<?> redirectByShortUrl(@PathVariable String shortnerId) {
        var origin = ss.getOriginalUrlFromShortUrl(shortnerId);
        return ResponseEntity
                .status(HttpStatus.MOVED_PERMANENTLY)
                .location(URI.create(origin))
                .build();
    }
}
