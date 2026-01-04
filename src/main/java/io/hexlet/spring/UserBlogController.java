package io.hexlet.spring;

import io.hexlet.spring.Model.UserBlog;
import io.hexlet.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserBlogController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/usersBlog")
    public List<UserBlog> getAllusers() {
        return userRepository.findAll();
    }

    @PostMapping("/usersBlog")
    @ResponseStatus(HttpStatus.CREATED)
    public List<UserBlog> create(@RequestBody UserBlog userBlog) {
        userRepository.save(userBlog);
        return userRepository.findAll();
    }

    @GetMapping("/usersBlog/{id}")
    public Optional<UserBlog> showUser(@PathVariable String id) {
        return userRepository.findById(Long.parseLong(id));
    }

    @DeleteMapping("/usersBlog/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        userRepository.deleteById(Long.parseLong(id));
    }

    @PutMapping("/usersBlog/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<UserBlog> updateUserBlog(@PathVariable Long id, @RequestBody UserBlog userBlog) {
        var userResult = userRepository.findById(id);
        if (userResult.isPresent()) {
            userResult.get().setLastName(userBlog.getLastName());
            userResult.get().setFirstName((userBlog.getFirstName()));
        }

        userRepository.save(userResult.get());

        return userRepository.findById(id);

    }

}
