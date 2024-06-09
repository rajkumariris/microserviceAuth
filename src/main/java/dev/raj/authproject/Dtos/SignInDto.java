package dev.raj.authproject.Dtos;

import dev.raj.authproject.Models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInDto {
    private String email;
    private String password;

    public static SignInDto from(User user){
        SignInDto signInDto = new SignInDto();
        signInDto.setEmail(user.getEmail());
        signInDto.setPassword(user.getPassword());
        return signInDto;
    }
}
