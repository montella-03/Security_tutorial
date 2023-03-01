package com.secure.demo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecureController {
    @GetMapping("/")
    public String hello(){
        return "<h1>Hello world</h1>";
    }
    @GetMapping("/user")
    public String user(){
        return "<h1>Hello user's world</h1>";
    }
    @GetMapping("/admin")
    public String admin(){
        return "<h1>Hello admins' world</h1>";
    }

}
