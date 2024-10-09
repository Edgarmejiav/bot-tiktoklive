package com.lega.bottiktok.controller;


import com.lega.bottiktok.service.TikTokLiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
public class TikTokLiveController {

    private final TikTokLiveService tikTokLiveService;
    @Autowired
    public TikTokLiveController(TikTokLiveService tikTokLiveService) {
        this.tikTokLiveService = tikTokLiveService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/comments/{name}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<Flux<String>> getComments(@PathVariable String name) {
        if (name == null) {
           return ResponseEntity.badRequest().build();
        }
        Flux<String> commentsFlux = tikTokLiveService.getComments(name)
                .onErrorReturn("Unable to fetch comments");
        return ResponseEntity.ok(commentsFlux);
    }
}