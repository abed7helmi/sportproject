package org.sid.emergencynotificationagentservice.repo;

import org.sid.emergencynotificationagentservice.entities.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public interface CoachRepository extends JpaRepository<Coach,Long> {
}
