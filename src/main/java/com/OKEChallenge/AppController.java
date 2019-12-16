package com.OKEChallenge;

import com.OKEChallenge.nlp.NamedEntityRecognizer;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Set;

@RestController
public class AppController {

    @PostMapping("/api/result")
    @ResponseBody
    public String result(@RequestBody ClientRequest clientRequest) {
        System.out.println(String.format("Query send by client %s", clientRequest.getText()));
        NamedEntityRecognizer ner = new NamedEntityRecognizer();
        Set<String> result = ner.getResult(clientRequest.getText());
        System.out.println("The result is: " + result);
        return String.join(" and", result);
    }

    @GetMapping("/api/hello")
    public String hello() {
        return "Hello, the time at the server is now " + new Date() + "\n";
    }
}
