package com.azmoon.notebook.repository;

import com.azmoon.notebook.model.Role;
import com.azmoon.notebook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
