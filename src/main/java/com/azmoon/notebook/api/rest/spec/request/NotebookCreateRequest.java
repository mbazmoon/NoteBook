package com.azmoon.notebook.api.rest.spec.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
public class NotebookCreateRequest {
    private List<String> tags;
    private Integer debit;
    private Integer credit;
    @NotBlank
    private String description;
}
