package com.azmoon.notebook.service;

import com.azmoon.notebook.exception.RoleNotFoundException;
import com.azmoon.notebook.exception.UserNotFoundException;
import com.azmoon.notebook.entity.User;
import org.springframework.data.domain.Page;

public interface UserService {
    User save(User user);

    void addRoleToUser(String userId, String roleName) throws UserNotFoundException, RoleNotFoundException;

    User getByUsername(String username) throws UserNotFoundException;

    User getByUserId(String userId) throws UserNotFoundException;

    Page<User> getAll(int page, int size);

}
