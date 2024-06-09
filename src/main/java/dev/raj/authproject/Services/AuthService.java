package dev.raj.authproject.Services;

import dev.raj.authproject.Dtos.UserDto;
import dev.raj.authproject.Models.User;

public interface AuthService {

    public String login(User user);

    public UserDto signup(String email, String password);
    void validate();
}
