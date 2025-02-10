package com.hype360kh.servergateway.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Fallback", description = "Fallback unavailable services")
@RestController
public class FallbackController {

    private static final Logger logger = LoggerFactory.getLogger(FallbackController.class);

    @GetMapping("/fallback/{serviceName}")
    public ResponseEntity<String> fallback(@PathVariable String serviceName) {
        logger.error("Fallback triggered for service: {}", serviceName);
        String message = String.format("%s service is temporarily unavailable. Please try again later.", serviceName);
        return new ResponseEntity<>(message, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
