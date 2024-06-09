package dev.raj.authproject.Models;

public enum SessionStatus {
    // if enumtype is original we store in db as active 1
    //expired 2 and loggedout 3
    ACTIVE, //1
    EXPIRED, //2
    LOGGED_OUT ,//3

    INVALID

}
