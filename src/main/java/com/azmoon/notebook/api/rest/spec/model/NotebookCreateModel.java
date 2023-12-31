package com.azmoon.notebook.api.rest.spec.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class NotebookCreateModel {
    private List<String> tags;
    private String username;
    private Integer debit;
    private Integer credit;
    private String description;
}
