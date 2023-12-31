package com.azmoon.notebook.api.rest.spec.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
public class TagCreateRequest {
    @NotBlank
    private String name;
}
