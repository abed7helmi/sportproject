package org.sid.userprofileservice.repo;

import org.sid.userprofileservice.entities.CoachProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoachProfileRepository extends JpaRepository<CoachProfile, Integer> {
}
