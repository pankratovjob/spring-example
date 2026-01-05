package io.hexlet.spring;

import io.hexlet.spring.Model.Post;
import io.hexlet.spring.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class PostController {
@Autowired
private PostRepository postRepository;

@PostMapping("posts")
@ResponseStatus(HttpStatus.CREATED)
public List<Post> create(@RequestBody Post post) {
    postRepository.save(post);
    return postRepository.findAll();

}

@GetMapping("posts")
public List<Post> returnAll() {
    return postRepository.findAll();
}

@GetMapping("posts/{id}")
public Post returnById(@PathVariable Long id) {
    if(postRepository.findById(id).isPresent()) {
        return postRepository.findById(id).get();
    } else {
        throw new RuntimeException("No posts with this ID");
    }
}

@PutMapping("posts/{id}")
@ResponseStatus(HttpStatus.OK)
public Post updatePost(@PathVariable Long id, @RequestBody Post targetPost) {
    var temp = postRepository.findById(id);
    if (temp.isPresent()) {
        temp.get().setContent(targetPost.getContent());
        temp.get().setTitle(targetPost.getTitle());
        temp.get().setPublished(targetPost.isPublished());
        postRepository.save(temp.get());
    }

    return postRepository.findById(id).get();
}

@DeleteMapping("posts/{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public List<Post> deleteById(@PathVariable Long id) {
    postRepository.deleteById(id);
    return postRepository.findAll();
}

}
