package io.hexlet.spring;

import io.hexlet.spring.Model.UserBlog;
import io.hexlet.spring.repository.UserBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserBlogController {
    @Autowired
    private UserBlogRepository userBlogRepository;

    @GetMapping("/usersBlog")
    public List<UserBlog> getAllUsersBlog() {
        return userBlogRepository.findAll();
    }

    @PostMapping("/usersBlog")
    @ResponseStatus(HttpStatus.CREATED)
    public List<UserBlog> create(@RequestBody UserBlog userBlog) {
        userBlogRepository.save(userBlog);
        return userBlogRepository.findAll();
    }

    @GetMapping("/usersBlog/{id}")
    public Optional<UserBlog> showUser(@PathVariable String id) {
        return userBlogRepository.findById(Long.parseLong(id));
    }

    @DeleteMapping("/usersBlog/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        userBlogRepository.deleteById(Long.parseLong(id));
    }

    @PutMapping("/usersBlog/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<UserBlog> updateUserBlog(@PathVariable Long id, @RequestBody UserBlog userBlog) {
        var userResult = userBlogRepository.findById(id);
        if (userResult.isPresent()) {
            userResult.get().setLastName(userBlog.getLastName());
            userResult.get().setFirstName((userBlog.getFirstName()));
        }

        userBlogRepository.save(userResult.get());

        return userBlogRepository.findById(id);

    }

}
