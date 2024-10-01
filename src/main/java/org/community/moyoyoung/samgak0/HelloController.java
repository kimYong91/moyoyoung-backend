package org.community.moyoyoung.samgak0;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public HelloResponse sayHello() {
        return new HelloResponse("Hello World");
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HelloResponse {
        private String message;
    }

}
