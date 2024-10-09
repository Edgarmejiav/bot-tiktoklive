package com.lega.bottiktok.service;

import io.github.jwdeveloper.tiktok.TikTokLive;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Service
public class TikTokLiveService {
    private final Sinks.Many<String> commentSink = Sinks.many().multicast().onBackpressureBuffer();
    private final Flux<String> commentFlux = commentSink.asFlux();

    public Flux<String> getComments(String hostName) {
        try {
            TikTokLive.newClient(hostName).onComment((liveClient, event) -> {
                String comment = event.getText();
                System.out.println("Comment: " + comment);
                commentSink.tryEmitNext(comment);
            }).buildAndConnect();
        } catch (Exception e) {
            System.err.println("Error al conectar con TikTok: " + e.getMessage());
            commentSink.tryEmitError(e);
        }
        return commentFlux;
    }
}
