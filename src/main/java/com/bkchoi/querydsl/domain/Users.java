package com.bkchoi.querydsl.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class Users {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String name;

    private String email;

    private boolean status;

    private int phone;

    private String teamCode;

    @Column(length = 4000)
    private String msg;

    @Builder
    public Users(String name, String email, boolean status, int phone,String teamCode, String msg) {
        this.name = name;
        this.email = email;
        this.status = status;
        this.phone = phone;
        this.teamCode = teamCode;
        this.msg = msg;
    }
}
