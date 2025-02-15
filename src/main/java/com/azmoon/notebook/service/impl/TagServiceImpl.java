package com.azmoon.notebook.service.impl;

import com.azmoon.notebook.entity.Tag;
import com.azmoon.notebook.repository.TagRepository;
import com.azmoon.notebook.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;


    @Override
    @CacheEvict(value = "notebook",allEntries = true)
    public Tag getOrCreate(String tagName) {
        Optional<Tag> tag = tagRepository.findByName(tagName);
        return tag.orElseGet(() -> tagRepository.save(Tag.builder().name(tagName).build()));
    }

    @Override
    public Page<Tag> getAll(int page, int size) {
        return tagRepository.findAll(PageRequest.of(page, size));
    }
}
