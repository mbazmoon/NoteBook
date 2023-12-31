package com.azmoon.notebook.service;

import com.azmoon.notebook.exception.NotebookNotFoundException;
import com.azmoon.notebook.model.Notebook;
import com.azmoon.notebook.model.Tag;
import com.azmoon.notebook.model.User;
import org.springframework.data.domain.Page;

public interface NotebookService {
    void addTag(String notebookNumber, String tagName) throws NotebookNotFoundException;

    Page<Notebook> getByTag(User owner, Tag tag, int page, int size);

    Notebook getByNumber(String notebookNumber) throws NotebookNotFoundException;

    Page<Notebook> getAll(User owner, int page, int size);

    Notebook save(Notebook notebook);

}
