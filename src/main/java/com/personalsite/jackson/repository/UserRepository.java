package com.personalsite.jackson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personalsite.jackson.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
}
