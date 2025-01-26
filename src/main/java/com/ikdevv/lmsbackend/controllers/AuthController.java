package com.ikdevv.lmsbackend.controllers;


import com.ikdevv.lmsbackend.entities.User;
import com.ikdevv.lmsbackend.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private AuthService service;

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return service.verify(user);
    }

    @PostMapping("/register")
    public User register(@RequestBody User user)
    {
        return service.register(user);
    }
}
