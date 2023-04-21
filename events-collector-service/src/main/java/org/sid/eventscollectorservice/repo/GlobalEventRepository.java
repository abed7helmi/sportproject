package org.sid.eventscollectorservice.repo;

import org.sid.eventscollectorservice.entities.GlobalEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface GlobalEventRepository extends MongoRepository<GlobalEvent,String>{
}
