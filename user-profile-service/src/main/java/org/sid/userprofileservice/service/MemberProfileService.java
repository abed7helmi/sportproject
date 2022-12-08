package org.sid.userprofileservice.service;


import lombok.extern.slf4j.Slf4j;
import org.sid.userprofileservice.entities.Member;
import org.sid.userprofileservice.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MemberProfileService {

    private final UserRepository userProfileRepository;

    @Autowired
    public MemberProfileService(UserRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public Member addMember(Member userProfile){
        return userProfileRepository.save(userProfile);
    }

    public Member findMemberById(Long id){
        return userProfileRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("User id " + id + " was not found")
        );
    }
}
