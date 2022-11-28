package org.sid.mock.repositories;

import org.sid.mock.models.Coach;
import org.sid.mock.models.HrSensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface HrSensorRepository extends JpaRepository<HrSensor,Long> {
}
