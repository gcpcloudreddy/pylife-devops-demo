package com.example.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "Greetings and Welcome to DevOPS with AWS Demo - July 25th 2024 - Changed the Code to V2 !";
    }

}
