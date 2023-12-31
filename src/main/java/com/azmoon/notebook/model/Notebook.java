package com.azmoon.notebook.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
@Entity
@Table(name = "notebook")
public class Notebook extends JpaBaseEntity {

    @ManyToMany(fetch = FetchType.EAGER)
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
