package org.sid.emergencynotificationagentservice.repo;

import org.sid.emergencynotificationagentservice.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public interface MemberRepository extends JpaRepository<Member,Long> {
}
