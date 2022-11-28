package org.sid.heartrateworkerservice.repo;

import org.sid.heartrateworkerservice.models.Member;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="user-profile-service")
public interface UserRestClientService {

    @GetMapping("/members/{id}")
    public Member mumberById(@PathVariable Long id);
}
