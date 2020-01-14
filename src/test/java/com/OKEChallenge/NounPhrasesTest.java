package com.OKEChallenge;

import com.OKEChallenge.nlp.NamedEntityRecognizer;
import com.OKEChallenge.nlp.NounPhraseExtractor;
import com.OKEChallenge.nlp.Pipeline;
import com.OKEChallenge.nlp.SentenceRecognizer;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class NounPhrasesTest {

    @Test
    public void nounPhraseOfString() {
        Pipeline.getPipeline();
        NounPhraseExtractor nounPhraseExtractor = new NounPhraseExtractor();
        NamedEntityRecognizer ner = new NamedEntityRecognizer();
        ner.getResult("Donald Trump is a president of the United States");
        SentenceRecognizer sr = new SentenceRecognizer();
        List<String> nps = nounPhraseExtractor.getNpsFromText(sr.getSentences("Donald Trump is a president of the United States").get(0));
        List<Map<String, String>> results = new LinkedList<>();
        nps.stream()
                .filter(np -> !np.isEmpty())
                .forEach(np -> results.addAll(SparqlQuery.executeQuery(np.replace(" ", "_"))));

        nps.forEach(System.out::println);
        for (Map<String, String> result : results) {
            System.out.println(result.entrySet());
        }
    }

}
