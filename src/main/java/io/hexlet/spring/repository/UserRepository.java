package io.hexlet.spring.repository;

import io.hexlet.spring.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}