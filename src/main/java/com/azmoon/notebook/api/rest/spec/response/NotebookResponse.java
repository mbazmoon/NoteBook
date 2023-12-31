package com.azmoon.notebook.api.rest.spec.response;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class NotebookResponse extends BaseResponseService {
    private List<String> tags;
    private Integer debit;
    private Integer credit;
    private String description;
    private Instant creationDate;
    private Instant lastModificationDate;
}
