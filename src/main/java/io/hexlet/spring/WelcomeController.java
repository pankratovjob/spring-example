package io.hexlet.spring;

import io.hexlet.spring.Model.Admins;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WelcomeController {
    @Value("${app.welcome-message}")
    private String welcomeMessage;

    @Value("${my-option.admin-email}")
    private String adminEmail;

    @GetMapping("/welcome")
    public String welcome() {
        return welcomeMessage;
    }

    @GetMapping("/boom")
    public String boom() {
        return adminEmail;
    }

    @Autowired // Аннотация нужна для автоподстановки объекта
    private Admins admins;
    @GetMapping("/zz")
    public List<String> showAdmins() {
        if(admins.getAdmins().isEmpty()) {
            throw new RuntimeException("No admins in properties");
        }
        var result = admins.getAdmins().stream().sorted().toList();
        return result;
    }

}

