package com.example.freelance_platform.models;

import java.util.Date;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Component
@Entity
@Getter
@Setter
@NoArgsConstructor
// entities are not spring beans
public class Task {
    @Id
    @GeneratedValue
    private Long Id;
    private String name;
    private String description;
    private Long duration_in_hrs;
    private Double remuneration;
    private String task_document_link;
    @ManyToOne
    @JoinColumn(name = "email")
    private User user;

}
