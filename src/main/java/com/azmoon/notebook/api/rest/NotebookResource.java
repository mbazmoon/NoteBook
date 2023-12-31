package com.azmoon.notebook.api.rest;

import com.azmoon.notebook.api.rest.mapper.UserMapper;
import com.azmoon.notebook.api.rest.spec.AddRoleToUserRequest;
import com.azmoon.notebook.api.rest.spec.RoleCreateRequest;
import com.azmoon.notebook.api.rest.spec.RoleCreateResponse;
import com.azmoon.notebook.api.rest.spec.UserCreateRequest;
import com.azmoon.notebook.api.rest.spec.UserCreateResponse;
import com.azmoon.notebook.exception.RoleNotFoundException;
import com.azmoon.notebook.exception.UserNotFoundException;
import com.azmoon.notebook.model.Notebook;
import com.azmoon.notebook.model.User;
import com.azmoon.notebook.service.NotebookService;
import com.azmoon.notebook.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Notebook", description = "notebook management Api")
@RequestMapping(path = "v1/notebook")
public class NotebookResource {
    private final UserService userService;
    private final NotebookService notebookService;
    private final UserMapper userMapper;

    @GetMapping("/all")
    public ResponseEntity<Page<Notebook>> getAll(@RequestParam int page, @RequestParam int size) throws UserNotFoundException {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getByUsername(username);
        return ResponseEntity.ok().body(notebookService.getAll(user, page, size));
    }

//    @PostMapping("/user")
//    public ResponseEntity<UserCreateResponse> addUser(@Valid @RequestBody UserCreateRequest request) {
//        return ResponseEntity.ok().body(userMapper.toUserResponse(userService.save(userMapper.ToUserEntity(request))));
//    }
//
//    @PostMapping("/role")
//    public ResponseEntity<RoleCreateResponse> addRole(@Valid @RequestBody RoleCreateRequest request) {
//        return ResponseEntity.ok().body(userMapper.toRoleResponse(roleService.save(userMapper.toRoleEntity(request))));
//    }
//
//    @PostMapping("/user/role/add-to-user")
//    public ResponseEntity addRoleToUser(@Valid @RequestBody AddRoleToUserRequest request) throws UserNotFoundException, RoleNotFoundException {
//        userService.addRoleToUser(request.getUserId(), request.getRoleName());
//        return ResponseEntity.ok(HttpStatus.OK);
//    }

}
