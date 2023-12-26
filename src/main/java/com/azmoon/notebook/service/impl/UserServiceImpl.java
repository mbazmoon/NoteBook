package com.azmoon.notebook.service.impl;

import com.azmoon.notebook.model.Role;
import com.azmoon.notebook.model.User;
import com.azmoon.notebook.repository.RoleRepository;
import com.azmoon.notebook.repository.UserRepository;
import com.azmoon.notebook.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public User save(User user) {
        log.debug("save user with username:{} to DB", user.getUsername());
        return userRepository.save(user);
    }

    @Override
    public void addRoleToUser(String userId, String roleName) {
        User user = userRepository.findByUserId(userId);
        log.debug("add role:{} to user:{}", roleName, user.getUsername());
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
        userRepository.save(user);
    }

    @Override
    public User getByUsername(String username) {

        return userRepository.findByUsername(username);
    }

    @Override
    public User getByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public Page<User> getAll(int page, int size) {
        return userRepository.findAll(PageRequest.of(page, size));
    }
}
