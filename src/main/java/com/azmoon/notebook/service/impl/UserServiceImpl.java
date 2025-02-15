package com.azmoon.notebook.service.impl;

import com.azmoon.notebook.exception.RoleNotFoundException;
import com.azmoon.notebook.exception.UserNotFoundException;
import com.azmoon.notebook.entity.Role;
import com.azmoon.notebook.entity.User;
import com.azmoon.notebook.repository.RoleRepository;
import com.azmoon.notebook.repository.UserRepository;
import com.azmoon.notebook.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder encoder;

    @Override
    @CacheEvict(value = "user",allEntries = true)
    public User save(User user) {
        log.debug("save user with username:{} to DB", user.getUsername());
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    @CacheEvict(value = "user",allEntries = true)
    public void addRoleToUser(String userId, String roleName) throws UserNotFoundException, RoleNotFoundException {
        User user = getByUserId(userId);
        log.debug("add role:{} to user:{}", roleName, user.getUsername());
        Role role = roleRepository.findByName(roleName).orElseThrow(() -> new RoleNotFoundException(String.format("role with Name: %s not found", roleName)));
        user.getRoles().add(role);
        userRepository.save(user);
    }

    @Override
    public User getByUsername(String username) throws UserNotFoundException {

        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(String.format("user with username %s not found", username)));
    }

    @Override
    public User getByUserId(String userId) throws UserNotFoundException {

        return userRepository.findByUserId(userId).orElseThrow(() -> new UserNotFoundException(String.format("user with userId %s not found", userId)));
    }

    @Override
    public Page<User> getAll(int page, int size) {
        return userRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("user with username %s not found", username)));
        List<SimpleGrantedAuthority> simpleGrantedAuthorityList = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), simpleGrantedAuthorityList);
    }
}
