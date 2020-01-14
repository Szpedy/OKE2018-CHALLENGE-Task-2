package com.OKEChallenge;

import com.OKEChallenge.nlp.NamedEntityRecognizer;
import com.OKEChallenge.nlp.NounPhraseExtractor;
import com.OKEChallenge.nlp.SentenceRecognizer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import edu.stanford.nlp.util.CoreMap;
import org.springframework.web.bind.annotation.*;

import javax.json.Json;
import java.util.*;

@RestController
public class AppController {
    private NamedEntityRecognizer ner = new NamedEntityRecognizer();
    private SentenceRecognizer sr = new SentenceRecognizer();
    private NounPhraseExtractor npe = new NounPhraseExtractor();

    @PostMapping("/api/result")
    @ResponseBody
    public String result(@RequestBody ClientRequest clientRequest) {
        Map<String, String> allResults = new HashMap<>();
        String clientText = clientRequest.getText();
        System.out.println(String.format("Query send by client %s", clientText));

        // Get sentences from text
        List<CoreMap> sentences = sr.getSentences(clientText);
        GsonBuilder gsonMapBuilder = new GsonBuilder();
        Gson gsonObject = gsonMapBuilder.create();
        sentences.forEach(sentence -> npe.getNpsFromText(sentence).forEach(np -> allResults.putAll(ner.getResultsAsMap(np))));
        System.out.println("Result from getMap:" + allResults.toString());
//        String result = ner.getResult(clientText);
//        System.out.println("The result is: " + result);
        return gsonObject.toJson(allResults);
    }

    @GetMapping("/api/hello")
    public String hello() {
        return "Hello, the time at the server is now " + new Date() + "\n";
    }
}
