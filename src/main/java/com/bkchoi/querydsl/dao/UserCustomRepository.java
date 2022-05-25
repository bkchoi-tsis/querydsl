package com.bkchoi.querydsl.dao;

import com.bkchoi.querydsl.domain.Users;
import com.bkchoi.querydsl.dto.respons.TeamUsers;

import java.util.List;

public interface UserCustomRepository {
    List<Users> findAllFetchJoin();

    List<TeamUsers> findTeamUsers();
}
