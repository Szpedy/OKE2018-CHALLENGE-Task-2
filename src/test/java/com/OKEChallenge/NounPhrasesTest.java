package com.OKEChallenge;

import com.OKEChallenge.nlp.NounPhraseExtractor;
import com.OKEChallenge.nlp.Pipeline;
import edu.stanford.nlp.pipeline.Annotation;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class NounPhrasesTest {

    @Test
    public void nounPhraseOfString() {
        Annotation annotation = new Annotation("Donald Trump");
        Pipeline.getPipeline().annotate(annotation);
        NounPhraseExtractor nounPhraseExtractor = new NounPhraseExtractor();
        List<String> nps = nounPhraseExtractor.getNpsFromText("Donald Trump is a president of the united states");
        List<Map<String, String>> results = new LinkedList<>();
        nps.stream()
                .filter(np -> !np.isEmpty())
                .forEach(np -> results.addAll(SparqlQuery.executeQuery(np.replace(" ", "_"))));
        for (Map<String, String> result : results) {
            System.out.println(result.entrySet());
        }
    }

}
