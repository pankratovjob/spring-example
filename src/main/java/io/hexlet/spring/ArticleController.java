package io.hexlet.spring;

import io.hexlet.spring.Model.Article;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class ArticleController {
List<Article> articles = new ArrayList<Article>();

@PostMapping("/{id}/articles")
public ResponseEntity<Article> create(@PathVariable int id, @RequestBody Article article) {
    article.setUserId(id);
    articles.add(article);
    return ResponseEntity.status(HttpStatus.CREATED).body(article);
}

@GetMapping("/{id}/articles")
public ResponseEntity<List<Article>> allArticles(@PathVariable int id) {
    var articlesByUser = articles.stream().filter(p -> p.getUserId() == id).toList();
    return ResponseEntity.ok().body(articlesByUser);
}

}
