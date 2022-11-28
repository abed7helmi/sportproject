package org.sid.userprofileservice.repo;

import org.sid.userprofileservice.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public interface UserRepository extends JpaRepository<Member,Long> {
}
