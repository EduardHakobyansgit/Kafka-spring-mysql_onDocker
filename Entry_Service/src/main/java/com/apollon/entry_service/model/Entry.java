package com.apollon.entry_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity

public class Entry {

    @Positive
    @NotNull
    @Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull
    private String api;

    @Column(length = 200)
    private String description;

    private String auth;

    private String https;

    private String cors;

    @Column(length = 155)
    private String link;

    private String category;

}
