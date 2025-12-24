package io.hexlet.spring;

import io.hexlet.spring.Model.Post;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> home() {
        var welcomeHxt = "!Добро пожаловать в Hexlet Spring Blog!";
        return ResponseEntity.ok().body(welcomeHxt);
    }

    @GetMapping("/about")
    public ResponseEntity<String> about() {
        var welcomTxt = "This is simple Spring blog!";
        return ResponseEntity.ok()
                .body(welcomTxt);
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> create(@RequestBody Post post) {
        if (post.getTitle().length() < 3 || post.getContent().length() < 10) {
            throw new RuntimeException("Эй, заголовок/контент допиши");
        }
        posts.add(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Optional<Post>> show(@PathVariable String id) {
        var post = posts.stream()
                .filter(p -> p.getTitle().equals(id))
                .findFirst();
        if(post.isPresent()) {
            return ResponseEntity.ok().body(post);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/posts") // Список страниц
    public ResponseEntity<List<Post>> index(@RequestParam(defaultValue = "10") Integer limit) {
        return ResponseEntity.ok().body(posts.stream().limit(limit).toList());
    }

    @PutMapping("/posts/{id}") // Обновление страницы
    public ResponseEntity<Post> update(@PathVariable String id, @RequestBody Post data) {
        var maybePost = posts.stream()
                .filter(p -> p.getTitle().equals(id))
                .findFirst();
        if (maybePost.isPresent()) {
            var page = maybePost.get();
            page.setTitle(data.getTitle());
            page.setAuthor(data.getAuthor());
            page.setContent(data.getContent());
        }
        return ResponseEntity.ok().body(data);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Void> destroy(@PathVariable String id) {
        posts.removeIf(p -> p.getTitle().equals(id));
        return ResponseEntity.noContent().build();
    }

}



/*
Получения списка постов (GET /posts)
Получения одного поста по id (GET /posts/{id})
Создания поста (POST /posts)
Обновления поста (PUT /posts/{id})
Удаления поста (DELETE /posts/{id})
 */