package com.hype360kh.servicecatalog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class GreetTestController {
    @GetMapping
    public String greet() {
        return "Hello Wo2rld";
    }
}
