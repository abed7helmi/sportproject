package com.example.coachappjava;

public class SaveCoachDTO {
    private Long idCoach;
    private String email;
    private String coachFirstName;





    public SaveCoachDTO() {}

    public SaveCoachDTO(Long idCoach, String email, String coachFirstName) {
        this.idCoach = idCoach;
        this.email = email;
        this.coachFirstName = coachFirstName;
    }

    public Long getIdCoach() {
        return idCoach;
    }

    public void setIdCoach(Long idCoach) {
        this.idCoach = idCoach;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCoachFirstName() {
        return coachFirstName;
    }

    public void setCoachFirstName(String coachFirstName) {
        this.coachFirstName = coachFirstName;
    }
}
