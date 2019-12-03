package com.OKEChallenge;

import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class AppController {

    @PostMapping("/api/result")
    @ResponseBody
    String result(@RequestParam(value="query", defaultValue="") String query) {
        System.out.println(String.format("Query send by client %s", query));
        return "This should be a result of Broader Named Entity Identification and Linking";
    }

    @GetMapping("/api/hello")
    public String hello() {
        return "Hello, the time at the server is now " + new Date() + "\n";
    }
}
