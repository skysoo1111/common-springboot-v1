package com.study.common.springbootv1.sample.controller;

import com.study.common.springbootv1.domain.Member;
import com.study.common.springbootv1.domain.entity.MemberEntity;
import com.study.common.springbootv1.mapper.MemberMapper;
import com.study.common.springbootv1.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class MemberController {

    // JPA
    private final MemberRepository memberRepository;
    // Mybatis
    private final MemberMapper memberMapper;

    @PostMapping("/signin")
    public Member signin(@RequestBody MemberEntity singinUser,
                         HttpServletRequest request) {
        Member member = memberMapper.findByUid(singinUser.getUid());
        log.info("##### user.getId() : {}",member.getId());
        return member;
    }

    @PostMapping("/signup")
    public MemberEntity signup(@RequestBody MemberEntity singupUser,
                               HttpServletRequest request) {
        Optional.ofNullable(memberRepository.getMemberByUid(singupUser.getUid()))
                .orElseGet(() -> memberRepository.save(singupUser));

        return singupUser;
    }

    @GetMapping("/mybatis/member/{email}")
    public Member getMemberByUid(@PathVariable String email) {
        Member byUid = memberMapper.findByUid(email);
        return byUid;
    }

    @GetMapping("/jpa/member/{email}")
    public MemberEntity getMemberEntityByUid(@PathVariable String email) {
        return memberRepository.getMemberByUid(email);
    }

    @DeleteMapping("/signout/{email}")
    public void deleteMemberByUid(@PathVariable String email) {
        memberRepository.deleteMemberByUid(email);
    }
}
