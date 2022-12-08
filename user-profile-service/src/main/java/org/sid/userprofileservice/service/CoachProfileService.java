package org.sid.userprofileservice.service;

import org.sid.userprofileservice.entities.CoachProfile;
import org.sid.userprofileservice.repo.CoachProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoachProfileService {

    private final CoachProfileRepository coachProfileRepository;

    @Autowired
    public CoachProfileService(CoachProfileRepository coachProfileRepository) {
        this.coachProfileRepository = coachProfileRepository;
    }

    public CoachProfile addCoach(CoachProfile coachProfile){
        return coachProfileRepository.save(coachProfile);
    }

    public CoachProfile findCoachById(Integer id){
        return coachProfileRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("User id " + id + " was not found")
        );
    }
}
