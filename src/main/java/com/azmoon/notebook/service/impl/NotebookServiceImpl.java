package com.azmoon.notebook.service.impl;

import com.azmoon.notebook.api.rest.spec.model.NotebookCreateModel;
import com.azmoon.notebook.entity.Notebook;
import com.azmoon.notebook.entity.Tag;
import com.azmoon.notebook.entity.User;
import com.azmoon.notebook.exception.NotebookNotFoundException;
import com.azmoon.notebook.exception.UserNotFoundException;
import com.azmoon.notebook.repository.NotebookRepository;
import com.azmoon.notebook.service.NotebookService;
import com.azmoon.notebook.service.TagService;
import com.azmoon.notebook.service.UserService;
import com.azmoon.notebook.service.mapper.NotebookServiceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class NotebookServiceImpl implements NotebookService {
    private final NotebookRepository notebookRepository;
    private final TagService tagService;
    private final UserService userService;
    private final NotebookServiceMapper mapper;

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

    @Override
    @Transactional
    public Notebook add(NotebookCreateModel model) throws UserNotFoundException {
        List<Tag> tags = model.getTags().stream().map(tagService::getOrCreate).toList();
        User user = userService.getByUsername(model.getUsername());
        return notebookRepository.save(mapper.toNotebook(model, tags, user));
    }
}
