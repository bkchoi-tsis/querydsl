package com.bkchoi.querydsl.dao;

import com.bkchoi.querydsl.domain.Users;
import com.bkchoi.querydsl.dto.respons.TeamUsers;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.bkchoi.querydsl.domain.QUsers.users;
import static com.bkchoi.querydsl.domain.QTeams.teams;

@Repository
@RequiredArgsConstructor
public class UserCustomRepositoryImpl implements UserCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Users> findAllFetchJoin() {
        return jpaQueryFactory.selectFrom(users).fetch();
    }

    @Override
    public List<TeamUsers> findTeamUsers() {
        return jpaQueryFactory
                .select(Projections.fields(TeamUsers.class,
                        teams.name.as("teamName"),
                        teams.code,
                        teams.upperCode,
                        teams.ownerName,
                        teams.ownerCode,
                        users.name,
                        users.email,
                        users.status,
                        users.phone,
                        users.teamCode,
                        users.msg
                        ))
                .from(teams)
                .join(users).on(teams.code.eq(users.teamCode))
                .fetch();
    }

}
