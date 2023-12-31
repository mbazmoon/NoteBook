package com.azmoon.notebook.service.impl;

import com.azmoon.notebook.exception.NotebookNotFoundException;
import com.azmoon.notebook.exception.UserNotFoundException;
import com.azmoon.notebook.model.Notebook;
import com.azmoon.notebook.model.Role;
import com.azmoon.notebook.model.Tag;
import com.azmoon.notebook.model.User;
import com.azmoon.notebook.repository.NotebookRepository;
import com.azmoon.notebook.service.TagService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@Import({NotebookServiceImpl.class})
class NotebookServiceImplTest {
    @Autowired
    private NotebookServiceImpl notebookService;
    @MockBean
    private NotebookRepository notebookRepository;
    @MockBean
    private TagService tagService;

    @MockBean
    private UserServiceImpl userService;

    @Test
    void getByNumber() throws NotebookNotFoundException {
        Mockito.when(notebookRepository.findByNotebookNumber(any())).thenReturn(Optional.of(createNotebook()));
        notebookService.getByNumber("test");
        verify(notebookRepository, times(1)).findByNotebookNumber(any());
    }

     @Test
    void save() throws UserNotFoundException {
        Mockito.when(userService.getByUsername(any())).thenReturn(createUser());
        notebookService.save(createNotebook()
        );
        verify(notebookRepository, times(1)).save(any());
    }

    private Notebook createNotebook() {
        return Notebook.builder()
                .credit(100)
                .owner(createUser())
                .tags(List.of(Tag.builder().name("english class").build()))
                .description("I should solve my class training for chapter 5")
                .build();
    }

    private User createUser() {
        return User.builder().username("test").password("1234").roles(List.of(Role.builder().name("ADMIN").build())).build();
    }
}