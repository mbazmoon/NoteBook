package com.azmoon.notebook.service;

import com.azmoon.notebook.model.User;
import org.springframework.data.domain.Page;

public interface UserService {
    User save(User user);

    void addRoleToUser(String userId, String roleName);

    User getByUsername(String username);

    User getByUserId(String userId);

    Page<User> getAll(int page, int size);

}
