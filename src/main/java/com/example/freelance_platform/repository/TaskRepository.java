package com.example.freelance_platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.freelance_platform.models.Task;

import java.util.ArrayList;

import java.util.Optional;


@Repository
public interface TaskRepository extends JpaRepository<Task ,Long> {
    Optional<Task> findById(Long id);
    Optional<ArrayList<Task>>findByUser_Email(String email);

    
} 
