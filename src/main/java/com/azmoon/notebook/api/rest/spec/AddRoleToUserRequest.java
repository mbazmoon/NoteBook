package com.azmoon.notebook.api.rest.spec;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddRoleToUserRequest {
    @NotBlank
    private String roleName;
    @NotBlank
    private String userId;
}
