package com.azmoon.notebook.repository;

import com.azmoon.notebook.entity.Role;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Cacheable(value = "role", key = "#name")
    Optional<Role> findByName(String name);
}
