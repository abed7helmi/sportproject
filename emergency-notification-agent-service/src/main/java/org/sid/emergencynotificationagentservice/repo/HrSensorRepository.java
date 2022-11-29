package org.sid.emergencynotificationagentservice.repo;


import org.sid.emergencynotificationagentservice.entities.HrSensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface HrSensorRepository extends JpaRepository<HrSensor,Long> {
}
