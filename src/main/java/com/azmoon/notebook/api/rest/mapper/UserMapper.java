package com.azmoon.notebook.api.rest.mapper;

import com.azmoon.notebook.api.rest.spec.RoleCreateRequest;
import com.azmoon.notebook.api.rest.spec.RoleCreateResponse;
import com.azmoon.notebook.api.rest.spec.UserCreateRequest;
import com.azmoon.notebook.api.rest.spec.UserCreateResponse;
import com.azmoon.notebook.model.Role;
import com.azmoon.notebook.model.User;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface UserMapper {

    User ToUserEntity(UserCreateRequest request);

    UserCreateResponse toUserResponse(User user);

    RoleCreateResponse toRoleResponse(Role role);

    Role toRoleEntity(RoleCreateRequest request);
}
