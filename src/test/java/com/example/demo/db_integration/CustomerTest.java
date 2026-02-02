package com.example.demo.db_integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.MySqlTestContainerBase;

@SpringBootTest
@ActiveProfiles("test")
class CustomerTest extends MySqlTestContainerBase {

    @Autowired
    CustomerRepository repository;

    @Test
    void addCustomers() {
        repository.save(new Customer("Jack", "Bauer", "jack.bauer@gmail.com"));
        repository.save(new Customer("Chloe", "O'Brian", "chloe.brian@gmail.com"));
        repository.save(new Customer("Kim", "Bauer", "kim.bauer@gmail.com"));
        repository.save(new Customer("David", "Palmer", "david.palmer@gmail.com"));
        repository.save(new Customer("Mike", "Dessler", "mike.dessler@gmail.com"));

        Customer customer = repository.findById(1L);
        
        Assertions.assertEquals(1, customer.getId());
    }
}
