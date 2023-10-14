package com.example.awsdemoapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.awsdemoapp.Entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    
}
