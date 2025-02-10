package com.hype360kh.servicereport.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Greeting", description = "Greeting API Management")
@RestController
@RequestMapping("/api/v1/greeting")
public class GreetController {
    @GetMapping
    public String greet() {
        return "Hello World";
    }
}
