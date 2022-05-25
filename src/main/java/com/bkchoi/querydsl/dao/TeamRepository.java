package com.bkchoi.querydsl.dao;

import com.bkchoi.querydsl.domain.Teams;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Teams,Long> {

}
