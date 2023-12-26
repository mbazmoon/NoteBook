package com.azmoon.notebook.repository;

import com.azmoon.notebook.model.Role;
import com.azmoon.notebook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
