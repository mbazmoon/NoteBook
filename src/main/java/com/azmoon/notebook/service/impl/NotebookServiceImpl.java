package com.azmoon.notebook.service.impl;

import com.azmoon.notebook.exception.NotebookNotFoundException;
import com.azmoon.notebook.model.Notebook;
import com.azmoon.notebook.model.Tag;
import com.azmoon.notebook.model.User;
import com.azmoon.notebook.repository.NotebookRepository;
import com.azmoon.notebook.service.NotebookService;
import com.azmoon.notebook.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotebookServiceImpl implements NotebookService {
    private final NotebookRepository notebookRepository;
    private final TagService tagService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addTag(String notebookNumber, String tagName) throws NotebookNotFoundException {
        Notebook notebook = getByNumber(notebookNumber);
        Tag tag = tagService.getOrCreate(tagName);
        notebook.getTags().add(tag);
        notebookRepository.save(notebook);

    }

    @Override
    public Page<Notebook> getByTag(User owner, Tag tag, int page, int size) {
        return notebookRepository.findAllByTagsAndOwner(tag, owner, PageRequest.of(page, size));
    }

    @Override
    public Notebook getByNumber(String notebookNumber) throws NotebookNotFoundException {
        return notebookRepository.findByNotebookNumber(notebookNumber).orElseThrow(() -> new NotebookNotFoundException(String.format("not find notebook with number :%s", notebookNumber)));
    }

    @Override
    public Page<Notebook> getAll(User owner, int page, int size) {
        return notebookRepository.findAllByOwner(owner, PageRequest.of(page, size));
    }

    @Override
    public Notebook save(Notebook notebook) {
        return notebookRepository.save(notebook);
    }
}
