package com.bkchoi.querydsl.dto.respons;

import com.bkchoi.querydsl.dto.PageMetadataResponse;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class MemberListResponse {
    private final List<MemberResDTO> memberList;
    private final PageMetadataResponse pageMetadataResponse;
}
