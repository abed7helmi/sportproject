package org.sid.notificationchannelmanagerservice.event;

import org.sid.notificationchannelmanagerservice.event.CoachConnectedEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CoachConnectedEventRepository extends JpaRepository<CoachConnectedEvent,Long> {
}
