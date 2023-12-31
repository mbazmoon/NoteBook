package com.azmoon.notebook.api.rest.mapper;

import com.azmoon.notebook.api.rest.spec.model.NotebookCreateModel;
import com.azmoon.notebook.api.rest.spec.request.NotebookCreateRequest;
import com.azmoon.notebook.api.rest.spec.response.NotebookResponse;
import com.azmoon.notebook.entity.Notebook;
import com.azmoon.notebook.entity.Tag;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface NotebookApiMapper {

    NotebookCreateModel ToModel(NotebookCreateRequest request, String username);

    NotebookResponse toResponse(Notebook notebook);

    default List<String> toTags(List<Tag> tags){
        if (CollectionUtils.isEmpty(tags)){
            return null;
        }
        return tags.stream().map(Tag::getName).collect(Collectors.toList());
    }

    List<NotebookResponse>  toResponseList(List<Notebook> notebooks);
}
