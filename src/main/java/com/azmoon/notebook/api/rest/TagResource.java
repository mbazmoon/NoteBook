package com.azmoon.notebook.api.rest;

import com.azmoon.notebook.api.rest.mapper.TagApiMapper;
import com.azmoon.notebook.api.rest.spec.request.TagCreateRequest;
import com.azmoon.notebook.api.rest.spec.response.TagResponse;
import com.azmoon.notebook.service.TagService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(path = "v1/tag")
public class TagResource {
    private final TagService tagService;
    private final TagApiMapper mapper;

    @GetMapping("/all")
    public ResponseEntity<List<TagResponse>> getAll(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok().body(mapper.toResponseList(tagService.getAll(page, size).getContent()));
    }

    @PostMapping("")
    public ResponseEntity<TagResponse> create(@Valid @RequestBody TagCreateRequest request) {
        return ResponseEntity.ok().body(mapper.toResponse(tagService.getOrCreate(request.getName())));
    }
}
