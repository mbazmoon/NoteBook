package com.azmoon.notebook.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.Instant;
import java.util.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
@Entity
@Table(name = "notebook")
public class Notebook implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    @CreatedDate
    @Column(name = "create_date", columnDefinition = "datetime default NOW()")
    private Instant creationDate;

    @Column(name = "last_modify_date")
    @LastModifiedDate
    @NotNull
    private Instant lastModificationDate;


    @ManyToMany(fetch = FetchType.LAZY)
    @Builder.Default
    private List<Tag> tags = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "f_owner")
    private User owner;
    @NotNull
    @Builder.Default
    @Column(name = "notebook_number")
    private String notebookNumber = UUID.randomUUID().toString().replace("_", "").substring(0, 8).toUpperCase();

    @Column
    private Integer debit = 0;

    @Column
    private Integer credit = 0;

    @NotNull
    @Column(length = 500)
    private String description;
}
