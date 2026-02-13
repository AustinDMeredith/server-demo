package com.example.demo;

/* 3rd Party Imports */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Tells Spring that this is is a Spring-Boot Application, auto-configures it, and scans for components within the package 
// components can be declared and defined in other packages, as long as they are sub-packages of the package in which this file lives
@SpringBootApplication
public class Main {

    // This method starts the Application
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
	}
}
