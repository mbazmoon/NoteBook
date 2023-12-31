package com.azmoon.notebook.api.rest.mapper;

import com.azmoon.notebook.api.rest.spec.request.RoleCreateRequest;
import com.azmoon.notebook.api.rest.spec.response.RoleCreateResponse;
import com.azmoon.notebook.api.rest.spec.request.UserCreateRequest;
import com.azmoon.notebook.api.rest.spec.response.UserCreateResponse;
import com.azmoon.notebook.entity.Role;
import com.azmoon.notebook.entity.User;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User ToUserEntity(UserCreateRequest request);

    UserCreateResponse toUserResponse(User user);

    RoleCreateResponse toRoleResponse(Role role);

    Role toRoleEntity(RoleCreateRequest request);
}
