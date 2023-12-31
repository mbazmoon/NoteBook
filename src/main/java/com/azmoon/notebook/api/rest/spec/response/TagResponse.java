package com.azmoon.notebook.api.rest.spec.response;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class TagResponse extends BaseResponseService {
    private String name;
    private Instant creationDate;
    private Instant lastModificationDate;
}
