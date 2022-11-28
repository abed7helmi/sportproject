package org.sid.mock.controllers;

import org.sid.mock.models.Coach;
import org.sid.mock.models.Member;
import org.sid.mock.repositories.CoachRepository;
import org.sid.mock.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class MyDBController {
    @Autowired
    CoachRepository coachRepository;

    @Autowired
    MemberRepository memberRepository;

    @GetMapping(path = "/addbd")
    public void addBd(){
        System.out.println("wiiw");
        coachRepository.save(new Coach(null,"CoachSedak",null));
        coachRepository.save(new Coach(null,"CoachMounir",null));
        Coach c1 =coachRepository.findById(1L).get();
        Coach c2 =coachRepository.findById(2L).get();

        memberRepository.save(new Member(null, "MemberEmna",true,null,c1));
        memberRepository.save(new Member(null, "MemberAsma",true,null,c2));


    }

    @GetMapping(path = "/test")
    public void test(){
        //Coach c1 =coachRepository.findById(1L).get();
        List<Member> member = memberRepository.findAll();
        List<Coach> member2 = coachRepository.findAll();
        //System.out.println(c1);
    }
}
