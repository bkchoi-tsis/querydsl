package com.bkchoi.querydsl.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class Teams {

    @Id @GeneratedValue
    @Column(name = "team_id")
    private Long id;

    private String name;

    private String code;

    private String upperCode;

    private String ownerName;

    private String ownerCode;

    @Builder
    public Teams(String name, String code, String upperCode, String ownerName, String ownerCode) {
        this.name = name;
        this.code = code;
        this.upperCode = upperCode;
        this.ownerName = ownerName;
        this.ownerCode = ownerCode;
    }
}
