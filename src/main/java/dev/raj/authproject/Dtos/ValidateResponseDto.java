package dev.raj.authproject.Dtos;

import dev.raj.authproject.Models.SessionStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateResponseDto {

    private UserDto userDto;
    private SessionStatus status;
}
