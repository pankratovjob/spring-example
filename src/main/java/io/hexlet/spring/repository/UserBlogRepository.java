package io.hexlet.spring.repository;

import io.hexlet.spring.Model.UserBlog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBlogRepository extends JpaRepository<UserBlog, Long> {
}