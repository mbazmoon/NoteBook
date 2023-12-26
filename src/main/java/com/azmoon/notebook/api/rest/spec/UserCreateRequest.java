package com.azmoon.notebook.api.rest.spec;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateRequest {
    private String name;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
