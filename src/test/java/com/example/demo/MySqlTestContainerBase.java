package com.example.demo;

import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;

/* This is a containerized version of the database running as a service. 
 * Doing it this way keeps the username and password for the database from
 * being hardcoded for testing. If you set it up correctly, you can have this
 * DB replicate the same schema as the prod and dev DB.
 *
 * Extend your DB test class from this class to use the container in that class.
 * */
@Testcontainers
public abstract class MySqlTestContainerBase {

    @ServiceConnection
    @SuppressWarnings("resource")
    static final MySQLContainer<?> mysql =
        new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

}
