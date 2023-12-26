package com.azmoon.notebook.service;

import com.azmoon.notebook.model.Role;

import java.util.List;

public interface RoleService {
    Role getRoleByName(String name);
    List<Role> getAll();
    Role save(Role role);

}
