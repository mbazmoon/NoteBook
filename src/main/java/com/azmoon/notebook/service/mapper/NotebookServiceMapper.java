package com.azmoon.notebook.service.mapper;

import com.azmoon.notebook.api.rest.spec.model.NotebookCreateModel;
import com.azmoon.notebook.entity.Notebook;
import com.azmoon.notebook.entity.Tag;
import com.azmoon.notebook.entity.User;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface NotebookServiceMapper {

    @Mapping(target = "debit", source = "model.debit")
    @Mapping(target = "credit", source = "model.credit")
    @Mapping(target = "description", source = "model.description")
    @Mapping(target = "tags", source = "tags")
    @Mapping(target = "owner", source = "owner")
    @Mapping(target = "id", ignore = true)
    Notebook toNotebook(NotebookCreateModel model, List<Tag> tags, User owner);

}
