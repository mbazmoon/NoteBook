package com.azmoon.notebook.api.rest.spec;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateResponse {
    private String name;
    private String username;
    private String password;
    private String userId;
}
