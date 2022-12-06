package org.sid.heartrateworkerservice.repo;

import org.sid.heartrateworkerservice.dto.HrSensorDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HrSensorRepository extends MongoRepository<HrSensorDTO, Long> {
}
