package org.sid.heartratecollectorservice.event;


import org.sid.heartratecollectorservice.models.HrSensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface HrSensorEventRepository extends JpaRepository<HrSensor,Long> {
}
