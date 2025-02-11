package com.hype360kh.serviceprofile.service.restclient;

import org.springframework.web.bind.annotation.GetMapping;

public interface ProfileFeignClient {
    @GetMapping("/api/v1/greeting")
    String greeting();
}
