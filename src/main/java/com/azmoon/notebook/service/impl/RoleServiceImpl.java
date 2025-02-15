package com.azmoon.notebook.service.impl;

import com.azmoon.notebook.entity.Role;
import com.azmoon.notebook.repository.RoleRepository;
import com.azmoon.notebook.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.findByName(name).orElseThrow();
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    @CacheEvict(value = "role",allEntries = true)
    public Role save(Role role) {
        return roleRepository.save(role);
    }
}
