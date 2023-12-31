package com.azmoon.notebook.service;

import com.azmoon.notebook.model.Tag;

public interface TagService {
    Tag getOrCreate(String tagName);

}
