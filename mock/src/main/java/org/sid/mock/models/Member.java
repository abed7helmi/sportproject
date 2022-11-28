package org.sid.mock.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idMember;
    String memberFirstName;
    /*String memberLastName;
    String memberEmail;
    String memberPhone;*/
    Boolean memberSubscription;
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)

    private List<HrSensor> Hrs= new ArrayList<>();
    @ManyToOne()
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Coach coach;
}
