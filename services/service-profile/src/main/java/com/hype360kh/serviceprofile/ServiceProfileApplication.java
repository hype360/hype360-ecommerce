package com.hype360kh.serviceprofile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {
    "com.hype360kh.libcommonservices.services.exception",
    "com.hype360kh.serviceprofile"
})
public class ServiceProfileApplication {

  public static void main(String[] args) {
    SpringApplication.run(ServiceProfileApplication.class, args);
  }

}