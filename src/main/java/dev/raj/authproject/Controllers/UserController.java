package dev.raj.authproject.Controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/id")
    public void getUserByDetails(){

    }

    @PostMapping("/{id}/roles")
    public void getRoleofUser(){

    }
}
