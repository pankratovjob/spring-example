package io.hexlet.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    @Value("${app.welcome-message}")
    private String welcomeMessage;

    @Value("${myOption.admin-email}")
    private String adminEmail;

    @GetMapping("/welcome")
    public String welcome() {
        return welcomeMessage;
    }

    @GetMapping("/boom")
    public String boom() {
        return adminEmail;
    }
}
