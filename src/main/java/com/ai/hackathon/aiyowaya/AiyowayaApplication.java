package com.ai.hackathon.aiyowaya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class AiyowayaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiyowayaApplication.class, args);
    }

}
