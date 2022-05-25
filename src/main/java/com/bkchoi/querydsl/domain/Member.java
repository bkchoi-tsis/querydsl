package com.bkchoi.querydsl.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Getter
@Audited
@AuditOverride(forClass=BaseEntity.class)
@NoArgsConstructor
public class Member extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long id;

    private String fullName;

    private String userName;

    private String email;

    private String password;

    @Builder
    public Member(String fullName, String userName, String email, String password) {
        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public void update(String fullName,String email, String password){
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }
}
