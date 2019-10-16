package com.poc.demok8sconfigmapasfile;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @Value("${welcome.details}")
    private String welcomeDetails;

    @GetMapping("/welcome")
    public String welcome() {
        String welcomeMessage = System.getenv().getOrDefault("WELCOME_MESSAGE", "Default Message(if no value from config)");
        return "Message from ConfigMap: [" + welcomeMessage +  "] // Message from file:" + welcomeDetails;
    }

    @GetMapping("/")
    public String help() {
        return "Application: DemoK8sConfigmapAsFileApplication.. Call /welcome";
    }

}
