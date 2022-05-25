package com.bkchoi.querydsl.service;

import com.bkchoi.querydsl.dao.MemberRepository;
import com.bkchoi.querydsl.domain.Member;
import com.bkchoi.querydsl.dto.PageMetadataResponse;
import com.bkchoi.querydsl.dto.request.MemberRegDTO;
import com.bkchoi.querydsl.dto.request.MemberUpdateDTO;
import com.bkchoi.querydsl.dto.respons.MemberListResponse;
import com.bkchoi.querydsl.dto.respons.MemberResDTO;
import com.querydsl.core.types.ExpressionException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberService {

    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public MemberRegDTO createUser(MemberRegDTO memberRegDTO){

        Member member = Member.builder()
                .fullName(memberRegDTO.getFullName())
                .userName(memberRegDTO.getUserName())
                .email(memberRegDTO.getEmail())
                .password(memberRegDTO.getPassword())
                .build();

        this.memberRepository.save(member);

        return modelMapper.map(member, MemberRegDTO.class);
    }

    public MemberListResponse getUserList(Pageable pageable){

        Page<Member> memberList = this.memberRepository.findAll(pageable);

        List<MemberResDTO> regDTOList = memberList.stream().map(member -> {
            return modelMapper.map(member, MemberResDTO.class);
        }).collect(Collectors.toList());

        PageMetadataResponse pageMeta = PageMetadataResponse.builder()
                .currentPage(pageable.getPageNumber())
                .currentSize(pageable.getPageSize())
                .totalPage(memberList.getTotalPages())
                .totalElements(memberList.getTotalElements())
                .build();

        return new MemberListResponse(regDTOList, pageMeta);
    }

    @Transactional
    public MemberUpdateDTO modifyUser(MemberUpdateDTO user) throws Exception {
        Member findMember = this.memberRepository.findByUserName(user.getUserName()).orElseThrow(() -> new Exception("not found user"));

        findMember.update(user.getFullName(),user.getEmail(), user.getPassword());

        return modelMapper.map(findMember, MemberUpdateDTO.class);
    }
}
