
// the endpoint must be contained in the same package or sub-package as the main class
// this is because the @SpringBootApplication flag scans for components, but only in
// same package or sub-package from which the flag is placed.
package com.example.demo.endpoints;

/* 3rd Party Imports */
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/* 3rd Party Imports */

/* Java Imports */
import java.util.Map;
import java.time.Instant;
/* Java Imports */

// Tells Spring that this code describes an endpoint that should be made available over the web
@RestController
public class PingController {
    // This tells Spring to answer GET requests with this method sent to this defined address
    @GetMapping("/api/ping")
    public Map<String, Object> ping () {
        // Spring will convert Java Objects to json automaticly!
        return Map.of(
            "Message", "Server is Alive!",
            "TimeStamp", Instant.now().toString()
        );
    }
}
