package com.lega.bottiktok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;


@SpringBootApplication
@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class BotTiktokApplication {

    public static void main(String[] args) {
        SpringApplication.run(BotTiktokApplication.class, args);
    }

    @GetMapping("/hello/{name}")
    public String sayHello(@PathVariable String name) {
        return "Hola, " + name;
    }
}
