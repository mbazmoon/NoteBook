package com.azmoon.notebook.repository;

import com.azmoon.notebook.entity.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Cacheable(value = "user", key = "#username")
    Optional<User> findByUsername(String username);

    @Cacheable(value = "user", key = "#userId")
    Optional<User> findByUserId(String userId);
}
