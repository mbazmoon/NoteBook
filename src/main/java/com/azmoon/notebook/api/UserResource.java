package com.azmoon.notebook.api;

import com.azmoon.notebook.model.User;
import com.azmoon.notebook.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(path = "/v1/api/user")
public class UserResource {
    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<Page<User>> getAll(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok().body(userService.getAll(page, size));

    }

}
