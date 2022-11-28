package org.sid.heartratesecondworkerservice.repo;

import org.sid.heartratesecondworkerservice.models.Member;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="user-profile-service")
public interface UserRestClientService {

    @GetMapping("/memberscd 1/{id}")
    public Member mumberById(@PathVariable Long id);
}
