package com.example.freelance_platform.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.stereotype.Repository;

import com.example.freelance_platform.models.User;

@Repository
public interface UserRepository  extends  JpaRepository<User,Long> {
    Optional<User> findById (Long user_id);
    Optional<User> findByEmail (String user_email);


    
}
