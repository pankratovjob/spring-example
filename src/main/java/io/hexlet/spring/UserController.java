package io.hexlet.spring;

import io.hexlet.spring.Model.User;
import io.hexlet.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    } // 200

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if(!user.getEmail().isEmpty()) {
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } else {
            throw new RuntimeException("email не заполнено");
        }

    } // 201

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    } // 204
}
