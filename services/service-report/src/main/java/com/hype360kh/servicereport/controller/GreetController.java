package com.hype360kh.servicereport.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/greet")
public class GreetController {
    @GetMapping
    public String greet() {
        return "Hello World";
    }
}
