package com.azmoon.notebook.service;

import com.azmoon.notebook.entity.Tag;
import org.springframework.data.domain.Page;

import java.net.ContentHandler;

public interface TagService {
    Tag getOrCreate(String tagName);

    Page<Tag> getAll(int page, int size);

}
