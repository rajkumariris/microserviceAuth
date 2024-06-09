package dev.raj.authproject.Controllers;

import dev.raj.authproject.Dtos.*;
import dev.raj.authproject.Exceptions.UserAlreadyExists;
import dev.raj.authproject.Exceptions.UserNotFoundException;
import dev.raj.authproject.Models.SessionStatus;
import dev.raj.authproject.Services.AuthService;
import dev.raj.authproject.Services.AuthServiceImple;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
   AuthServiceImple authService;

    public AuthController(AuthServiceImple authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody SignInDto request) throws UserNotFoundException {
        // a arndom 20char string is geenerated;

       ResponseEntity<UserDto> userToken = authService.login(request.getEmail(), request.getPassword());
      return userToken;

    }

    @PostMapping("/Signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignUpRequestDto request) throws UserAlreadyExists{
       UserDto userDto=  authService.signup(request.getEmail(), request.getPassword());
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    //we asked team to delete a session but he delted wrong one now he has to revert
    // but if he deletes hard delted means completely deleted that then that is a big problem
    // instead we should do soft delete

    @PostMapping("/logout")
    public SessionStatus logout(@RequestBody ValidateTokenRequestDto request) {
       return  authService.logout(request.getToken(), request.getUserId());
    }

    @PostMapping("/validate")
    public ResponseEntity<ValidateResponseDto>  validate(@RequestBody ValidateTokenRequestDto request){
     // always postman json should be camelcase
       Optional<UserDto> userDto = authService.validate(request.getUserId(), request.getToken());
        // check if token exisits in db or not
        if(userDto.isEmpty()) {
            ValidateResponseDto response = new ValidateResponseDto();
            response.setStatus(SessionStatus.INVALID);
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        ValidateResponseDto response = new ValidateResponseDto();
        response.setStatus(SessionStatus.ACTIVE);
        response.setUserDto(userDto.get());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    //first we login with password and email then we get a jwt token
    // it has headers, payload, signature
    // it sends back to us with those 3 items
    // header has alogrithm like HS256(Hashing algorithm)
    // payload has the data
    // signature is the combination of header and payload +secret key
    // these all are encoded in base64 seperated with dot
    // hashcode(hs256) is used with secret key to generate signature

    // now it request productservice for data using this token
    // it has secret key stored in it
    // now productservice will check message(header+payload) + secrete key  = output signature(header+payload+secret)
}
