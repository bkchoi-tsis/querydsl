package com.bkchoi.querydsl.controller;

import com.bkchoi.querydsl.dto.request.MemberRegDTO;
import com.bkchoi.querydsl.dto.request.MemberUpdateDTO;
import com.bkchoi.querydsl.dto.respons.MemberListResponse;
import com.bkchoi.querydsl.dto.respons.MemberResDTO;
import com.bkchoi.querydsl.global.common.PageRequest;
import com.bkchoi.querydsl.service.MemberService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;
    private final ModelMapper modelMapper;

    @RequestMapping(value = "/v1/users", method = RequestMethod.POST)
    public ResponseEntity<MemberRegDTO> createUser(@RequestBody @Valid MemberRegDTO member, BindingResult bindingResult) throws Exception {

        if(bindingResult.hasErrors()){
            throw new Exception("binding error");
        }

        MemberRegDTO user = this.memberService.createUser(member);
        return ResponseEntity.ok(user);
    }

    @RequestMapping(value = "/v1/users/sample", method = RequestMethod.POST)
    public ResponseEntity<String> createUserSample() throws Exception {

        for(int i=0;i<30;i++){
            MemberRegDTO member = new MemberRegDTO();
            member.setFullName("bk choi"+i);
            member.setUserName("bk"+i);
            member.setEmail("bk"+i+"@tsis.co.kr");
            member.setPassword("1234567");
            this.memberService.createUser(member);
        }

        return ResponseEntity.ok("ok");
    }

    @RequestMapping(value = "/v1/users", method = RequestMethod.GET)
    public ResponseEntity<MemberListResponse> getUserList(PageRequest pageable){

        MemberListResponse userList = this.memberService.getUserList(pageable.of());

        return ResponseEntity.ok(userList);
    }

    @RequestMapping(value = "/v1/users", method = RequestMethod.PUT)
    public ResponseEntity<MemberUpdateDTO> modifyUser(@RequestBody @Valid MemberUpdateDTO member, BindingResult bindingResult) throws Exception {

        if(bindingResult.hasErrors()){
            throw new Exception("binding error");
        }
        MemberUpdateDTO user = this.memberService.modifyUser(member);

        return ResponseEntity.ok(user);
    }
}
