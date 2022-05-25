package com.bkchoi.querydsl.dao;

import com.bkchoi.querydsl.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Long> {
    
}
