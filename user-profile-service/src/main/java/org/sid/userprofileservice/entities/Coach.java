package org.sid.userprofileservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idCoach;
    String coachFirstName;
    /*String coachLastName;
    String coachEmail;
    String coachPhone;*/

    @OneToMany(mappedBy = "coach", fetch = FetchType.LAZY)
    //@JsonIgnore
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Member> members= new ArrayList<>();
}
