package com.azmoon.notebook.api.rest.mapper;

import com.azmoon.notebook.api.rest.spec.response.TagResponse;
import com.azmoon.notebook.entity.Tag;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface TagApiMapper {
    TagResponse toResponse(Tag tag);

    List<TagResponse> toResponseList(List<Tag> tags);
}
