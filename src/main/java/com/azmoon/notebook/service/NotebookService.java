package com.azmoon.notebook.service;

import com.azmoon.notebook.api.rest.spec.model.NotebookCreateModel;
import com.azmoon.notebook.exception.NotebookNotFoundException;
import com.azmoon.notebook.entity.Notebook;
import com.azmoon.notebook.entity.Tag;
import com.azmoon.notebook.entity.User;
import com.azmoon.notebook.exception.UserNotFoundException;
import org.springframework.data.domain.Page;

public interface NotebookService {
    void addTag(String notebookNumber, String tagName) throws NotebookNotFoundException;

    Page<Notebook> getByTag(User owner, Tag tag, int page, int size);

    Notebook getByNumber(String notebookNumber) throws NotebookNotFoundException;

    Page<Notebook> getAll(User owner, int page, int size);

    Notebook save(Notebook notebook);
    Notebook add(NotebookCreateModel model) throws UserNotFoundException;

}
