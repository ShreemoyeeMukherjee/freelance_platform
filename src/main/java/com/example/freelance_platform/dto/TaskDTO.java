package com.example.freelance_platform.dto;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class TaskDTO {
    private String name;
    private String description;
    private Long  duration_in_hrs;
    private Double remuneration;

    
}
