package com.azmoon.notebook.repository;

import com.azmoon.notebook.entity.Notebook;
import com.azmoon.notebook.entity.Tag;
import com.azmoon.notebook.entity.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotebookRepository extends JpaRepository<Notebook, Long> {

    Page<Notebook> findAllByOwner(User owner, Pageable pageable);

    Optional<Notebook> findByNotebookNumber(String notebookNumber);

    Page<Notebook> findAllByTagsAndOwner(Tag tag, User owner, Pageable pageable);
}
