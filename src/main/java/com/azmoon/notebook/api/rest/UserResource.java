package com.azmoon.notebook.api.rest;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.azmoon.notebook.api.rest.mapper.UserMapper;
import com.azmoon.notebook.api.rest.spec.request.AddRoleToUserRequest;
import com.azmoon.notebook.api.rest.spec.request.RoleCreateRequest;
import com.azmoon.notebook.api.rest.spec.response.RoleCreateResponse;
import com.azmoon.notebook.api.rest.spec.request.UserCreateRequest;
import com.azmoon.notebook.api.rest.spec.response.UserCreateResponse;
import com.azmoon.notebook.exception.RoleNotFoundException;
import com.azmoon.notebook.exception.UserNotFoundException;
import com.azmoon.notebook.entity.Role;
import com.azmoon.notebook.entity.User;
import com.azmoon.notebook.service.RoleService;
import com.azmoon.notebook.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "User", description = "user management Api")
@RequestMapping(path = "v1")
public class UserResource {
    private final UserService userService;
    private final RoleService roleService;
    private final UserMapper userMapper;

    @GetMapping("/user/all")
    public ResponseEntity<Page<User>> getAll(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok().body(userService.getAll(page, size));
    }

    @PostMapping("/user")
    public ResponseEntity<UserCreateResponse> addUser(@Valid @RequestBody UserCreateRequest request) {
        return ResponseEntity.ok().body(userMapper.toUserResponse(userService.save(userMapper.ToUserEntity(request))));
    }

    @PostMapping("/role")
    public ResponseEntity<RoleCreateResponse> addRole(@Valid @RequestBody RoleCreateRequest request) {
        return ResponseEntity.ok().body(userMapper.toRoleResponse(roleService.save(userMapper.toRoleEntity(request))));
    }

    @PostMapping("/user/role/add-to-user")
    public ResponseEntity addRoleToUser(@Valid @RequestBody AddRoleToUserRequest request) throws UserNotFoundException, RoleNotFoundException {
        userService.addRoleToUser(request.getUserId(), request.getRoleName());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/token/refresh")
    public void getAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refreshToken = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(refreshToken);
                String username = decodedJWT.getSubject();
                User user = userService.getByUsername(username);
                String accessToken = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURI().toString())
                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", accessToken);
                tokens.put("refresh_token", refreshToken);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception e) {
                log.error("error on login: {}", e.getMessage());
                response.setHeader("error", e.getMessage());
                response.setStatus(HttpStatus.FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", e.getMessage());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("refresh token is missing");
        }
    }


}
