package dev.raj.authproject.Dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateTokenRequestDto {
    private Long UserId;
    private String token;

}
