package dev.raj.authproject.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Session extends BaseModel{
    private  String token;
    private Date expiredAt;
    @ManyToOne
    private User user;
    @Enumerated(EnumType.ORDINAL) // in db session_status will be stored as 0,1,2,3
    private SessionStatus status; // if used string instead ordinal we store exact string of enums
    // which helps to save space
}
