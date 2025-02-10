package com.hype360kh.servergateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/product")
    public String productFallback() {
        return "Product Service is unavailable!";
    }

    @GetMapping("/order")
    public String orderFallback() {
        return "Order Service is unavailable!";
    }
}