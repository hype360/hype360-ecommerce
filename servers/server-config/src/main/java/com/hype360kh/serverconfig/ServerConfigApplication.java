package com.hype360kh.serverconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServerConfigApplication {

  public static void main(String[] args) {
    SpringApplication.run(ServerConfigApplication.class, args);
  }

}
