package com.poc.demok8sconfigmap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/welcome")
    public String welcome() {
        return System.getenv().getOrDefault("WELCOME_MESSAGE", "Default Message(if no value from config)");
    }

    @GetMapping("/")
    public String help() {
        return "Call /welcome";
    }

}
