package org.bankly;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class apiGateway {
    public static void main(String[] args) {
        SpringApplication.run(apiGateway.class, args);
    }
}