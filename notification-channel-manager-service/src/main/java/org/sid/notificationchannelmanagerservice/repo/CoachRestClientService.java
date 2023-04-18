package org.sid.notificationchannelmanagerservice.repo;

import org.sid.notificationchannelmanagerservice.entities.Coach;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="user-profile-service")
public interface CoachRestClientService {

    @GetMapping(path="/coaches/{id}")
    Coach coachById(@PathVariable Long id);
}
