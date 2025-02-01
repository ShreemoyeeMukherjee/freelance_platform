package com.example.freelance_platform.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue
    private Long Id;
    private String name;
    private String description;
    private Date start_date;
    private Date end_date;
    private Double remuneration;
    private String task_document_link;
}
