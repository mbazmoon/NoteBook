package com.azmoon.notebook.service.impl;

import com.azmoon.notebook.model.Tag;
import com.azmoon.notebook.repository.TagRepository;
import com.azmoon.notebook.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service@Slf4j@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;

    @Override
    public Tag getOrCreate(String tagName) {
        return tagRepository.findByName(tagName).orElse(tagRepository.save(Tag.builder().name(tagName).build()));
    }
}
