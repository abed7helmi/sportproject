package org.sid.userprofileservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.sid.userprofileservice.entities.Member;
import org.sid.userprofileservice.service.MemberProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class MemberController {

    private MemberProfileService memberProfileService;

    @Autowired
    public MemberController(MemberProfileService memberProfileService) {
        this.memberProfileService = memberProfileService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id){
        Member member = memberProfileService.findMemberById(id);
        log.info("user id find {}", member.getIdMember());
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Member> registerMember (@RequestBody Member member){
        Member newMember = memberProfileService.addMember(member);
        log.info("New user registration {}", newMember.getIdMember());
        return new ResponseEntity<>(newMember, HttpStatus.CREATED);
    }
}
