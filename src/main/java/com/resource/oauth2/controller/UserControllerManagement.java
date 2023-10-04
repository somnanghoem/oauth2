package com.resource.oauth2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllerManagement {

    @GetMapping("/user")
    public String hell(){
        return "eh";
    }
}
