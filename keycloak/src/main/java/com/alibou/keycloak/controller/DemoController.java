package com.alibou.keycloak.controller;


import com.alibou.keycloak.dto.KeycloakInput;
import com.alibou.keycloak.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/turksat")
@CrossOrigin
public class DemoController {

    private UserService userService;

    @Autowired
    public DemoController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String hello(){
        return "Hello from the first method of spring app";
    }


    @GetMapping("/hello-2")
    public String hello2(){
        return "Hello from the admin method!";
    }

    @PostMapping("/getToken")
    public  String getToken(@RequestBody KeycloakInput keycloakInput) throws IOException, InterruptedException {
        return userService.getToken(keycloakInput);
    }

}
