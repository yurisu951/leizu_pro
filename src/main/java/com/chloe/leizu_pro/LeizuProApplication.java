package com.chloe.leizu_pro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(value = {"com.example.leizu.bean"})
public class LeizuProApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeizuApplication.class, args);
    }

}
