package com.azmoon.notebook.api.rest;

import com.azmoon.notebook.api.rest.mapper.NotebookApiMapper;
import com.azmoon.notebook.api.rest.spec.request.NotebookCreateRequest;
import com.azmoon.notebook.api.rest.spec.response.NotebookResponse;
import com.azmoon.notebook.entity.User;
import com.azmoon.notebook.exception.BusinessException;
import com.azmoon.notebook.service.NotebookService;
import com.azmoon.notebook.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Notebook", description = "notebook management Api")
@RequestMapping(path = "v1/notebook")
public class NotebookResource {
    private final UserService userService;
    private final NotebookService notebookService;
    private final NotebookApiMapper mapper;

    @GetMapping("/all")
    public ResponseEntity<List<NotebookResponse>> getAll(@RequestParam int page, @RequestParam int size) throws BusinessException {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getByUsername(username);
        return ResponseEntity.ok().body(mapper.toResponseList(notebookService.getAll(user, page, size).getContent()));
    }

    @PostMapping("")
    public ResponseEntity<NotebookResponse> addNote(@Valid @RequestBody NotebookCreateRequest request) throws BusinessException {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok().body(mapper.toResponse(notebookService.add(mapper.ToModel(request, username))));
    }
}
