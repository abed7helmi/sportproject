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

        coachRepository.save(new Coach(null,"CoachSedak",null));
        coachRepository.save(new Coach(null,"CoachMounir",null));
        Coach c1 =coachRepository.findById(1L).get();
        Coach c2 =coachRepository.findById(2L).get();

        memberRepository.save(new Member(null, "MemberCedric","H",20,true,null,c1));
        memberRepository.save(new Member(null, "MemberMélanie","F",22,true,null,c1));
        memberRepository.save(new Member(null, "MemberEmna","F",27,true,null,c2));
        memberRepository.save(new Member(null, "MemberThomas","H",20,false,null,c1));
        memberRepository.save(new Member(null, "MemberDavid","H",60,true,null,c2));
        memberRepository.save(new Member(null, "MemberIsabelle","F",67,true,null,c2));
        memberRepository.save(new Member(null, "MemberHelmi","H",20,true,null,c1));



    }


}
