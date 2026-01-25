// the endpoint must be contained in the same package or sub-package as the main class
// this is because the @SpringBootApplication flag scans for components, but only in
// same package or sub-package from which the flag is placed.
package com.example.demo.endpoints;

/* 3rd Party Imports */
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
/* 3rd Party Imports */

/* Java Imports */
import java.util.Map;
/* Java Imports */

@RestController
public class EchoController {
    // This tells Spring to answer POST requests with this method sent to this defined address
    @PostMapping("/api/echo")
    // @RequestBody tells Spring to create a java object (in this case an EchoRquest object) with the request body. This is how we can deserialize POST requests in Spring
    public Map<String, Object> echo (@RequestBody EchoRequest request) {
        return Map.of(
            "Original", request.getText(),
            "Length", request.getText().length()
        );
    }  
}

// random comment

// curl -X POST http://localhost:8080/api/echo -H "Content-Type: application/json" -d '{"text":"hello spring"}'
