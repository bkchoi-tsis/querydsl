package com.bkchoi.querydsl.dto.request;

import lombok.Data;

@Data
public class MemberRegDTO {

    private String fullName;
    private String userName;
    private String email;
    private String password;
}
