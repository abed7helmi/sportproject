package org.sid.heartratesecondworkerservice.event;

import org.sid.heartratesecondworkerservice.event.HrSensorDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface HrEmergencyEventRepository extends JpaRepository<HrSensorDTO,Long> {
}
