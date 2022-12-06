package org.sid.heartratesecondworkerservice.repo;


import org.sid.heartratesecondworkerservice.dto.HrSensorDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HrSensorRepository extends MongoRepository<HrSensorDTO, Long> {
}
