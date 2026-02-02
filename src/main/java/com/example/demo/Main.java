package com.example.demo;

/* 3rd Party Imports */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.db_integration.Customer;
import com.example.demo.db_integration.CustomerRepository;

// Tells Spring that this is is a Spring-Boot Application, auto-configures it, and scans for components within the package 
// components can be declared and defined in other packages, as long as they are sub-packages of the package in which this file lives
@SpringBootApplication
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    // This method starts the Application
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
	}

    @Bean
    public CommandLineRunner demo(CustomerRepository repository) {
        return (args) -> {
            // save a few customers
            repository.save(new Customer("Jack", "Bauer", "jack.bauer@gmail.com"));
            repository.save(new Customer("Chloe", "O'Brian", "chloe.brian@gmail.com"));
            repository.save(new Customer("Kim", "Bauer", "kim.bauer@gmail.com"));
            repository.save(new Customer("David", "Palmer", "david.palmer@gmail.com"));
            repository.save(new Customer("Mike", "Dessler", "mike.dessler@gmail.com"));
            

            // fetch all customers
            logger.info("Customers found with findAll():");
            logger.info("-------------------------------");
            repository.findAll().forEach(customer -> {
                logger.info(customer.toString());
            });
            logger.info("");

            // fetch an individual customer by ID
            Customer customer = repository.findById(1L);
            logger.info("Customer found with findById(1L):");
            logger.info("--------------------------------");
            logger.info(customer.toString());
            logger.info("");

            // fetch customers by last name
            logger.info("Customer found with findByLastName('Bauer'):");
            logger.info("--------------------------------------------");
            repository.findByLastName("Bauer").forEach(bauer -> {
                logger.info(bauer.toString());
            });
            logger.info("");
        };
    }
}
