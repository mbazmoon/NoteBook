package com.azmoon.notebook.service;

import com.azmoon.notebook.model.Role;

public interface RoleService {
    Role getRoleByName(String name);

    Role save(Role role);

}
