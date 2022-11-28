package org.sid.mock.repositories;

import org.sid.mock.models.Coach;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CoachRepository extends JpaRepository<Coach,Long> {
}
