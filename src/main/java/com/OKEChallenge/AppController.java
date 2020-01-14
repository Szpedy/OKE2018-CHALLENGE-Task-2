package com.OKEChallenge;

import com.OKEChallenge.nlp.NamedEntityRecognizer;
import com.OKEChallenge.nlp.NounPhraseExtractor;
import com.OKEChallenge.nlp.SentenceRecognizer;
import edu.stanford.nlp.util.CoreMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class AppController {
    private NamedEntityRecognizer ner = new NamedEntityRecognizer();
    private SentenceRecognizer sr = new SentenceRecognizer();
    private NounPhraseExtractor npe = new NounPhraseExtractor();

    @PostMapping("/api/result")
    @ResponseBody
    public String result(@RequestBody ClientRequest clientRequest) {
        String clientText = clientRequest.getText();
        System.out.println(String.format("Query send by client %s", clientText));

        // Get sentences from text
        List<CoreMap> sentences = sr.getSentences(clientText);
        sentences.forEach(sentence -> npe.getNpsFromText(sentence).forEach(np -> ner.getResult(np)));
        String result = ner.getResult(clientText);
        System.out.println("The result is: " + result);
        return result;
    }

    @GetMapping("/api/hello")
    public String hello() {
        return "Hello, the time at the server is now " + new Date() + "\n";
    }
}
