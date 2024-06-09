package dev.raj.authproject.Exceptions;

public class UserAlreadyExists extends  Exception{

    public UserAlreadyExists(String message){
        super(message);
    }
}
