package io.hexlet.spring;

import io.hexlet.spring.Model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@Getter
@Setter
@AllArgsConstructor
public class UserController {
    List<User> users = new ArrayList<User>();
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(users);
    } // 200

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if(!user.getEmail().isEmpty()) {
            users.add(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } else {
            throw new RuntimeException("email не заполнено");
        }

    } // 201

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        users.removeIf(p -> p.getId() == id);
        return ResponseEntity.noContent().build();
    } // 204
}
