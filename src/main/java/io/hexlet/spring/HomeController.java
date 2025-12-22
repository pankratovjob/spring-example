package io.hexlet.spring;

import io.hexlet.spring.Model.Post;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
public class HomeController {
    public static void main(String[] args) {
        SpringApplication.run(HomeController.class, args);
    }

    private List<Post> posts = new ArrayList<Post>();

    @GetMapping("/")
    String home() {
        return "!Добро пожаловать в Hexlet Spring Blog!";
    }

    @GetMapping("/about")
    public String about() {
        return "This is simple Spring blog!";
    }

    @PostMapping("/posts")
    public Post create(@RequestBody Post post) {
        if (post.getTitle().length() < 3 || post.getContent().length() < 10) {
            throw new RuntimeException("Эй, заголовок/контент допиши");
        }
        return post;
    }

    @GetMapping("/posts/{id}")
    public Optional<Post> show(@PathVariable String id) {
        var post = posts.stream()
                .filter(p -> p.getTitle().equals(id))
                .findFirst();
        return post;
    }

    @GetMapping("/posts") // Список страниц
    public List<Post> index(@RequestParam(defaultValue = "10") Integer limit) {
        return posts.stream().limit(limit).toList();
    }

    @PutMapping("/posts/{id}") // Обновление страницы
    public Post update(@PathVariable String id, @RequestBody Post data) {
        var maybePost = posts.stream()
                .filter(p -> p.getTitle().equals(id))
                .findFirst();
        if (maybePost.isPresent()) {
            var page = maybePost.get();
            page.setTitle(data.getTitle());
            page.setAuthor(data.getAuthor());
            page.setContent(data.getContent());
        }
        return data;
    }

    @DeleteMapping("/posts/{id}")
    public void destroy(@PathVariable String id) {
        posts.removeIf(p -> p.getTitle().equals(id));
    }

}



/*
Получения списка постов (GET /posts)
Получения одного поста по id (GET /posts/{id})
Создания поста (POST /posts)
Обновления поста (PUT /posts/{id})
Удаления поста (DELETE /posts/{id})
 */