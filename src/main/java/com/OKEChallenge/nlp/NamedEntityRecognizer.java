package com.OKEChallenge.nlp;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.*;
import java.util.stream.Collectors;

public class NamedEntityRecognizer {

    private HashMap<String, String> collectRelevantTokens(List<CoreLabel> tokens) {
        List<CoreLabel> tokensFiltered = tokens
                .stream()
                .filter(token ->
                        !token.get(
                                CoreAnnotations.NamedEntityTagAnnotation.class)
                                .equalsIgnoreCase("O"))
                .collect(Collectors.toList());

        HashMap<String, String> token2Ner = new HashMap<>();

        for (CoreLabel token : tokensFiltered) {
            System.out.println(token.word() + "\t" + token.get(CoreAnnotations.NamedEntityTagProbsAnnotation.class));
            String ner = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
            token2Ner.put(token.originalText(), ner);
            System.out.println(token.originalText() + " its ner: " + ner);
        }

        System.out.println(tokens);
        System.out.println(token2Ner);

        return token2Ner;
    }

    public String getResult(String query) {
        StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();
        CoreDocument coreDocument = new CoreDocument(query);
        stanfordCoreNLP.annotate(coreDocument);
        List<CoreLabel> tokens = coreDocument.tokens();
        return collectRelevantTokens(tokens).toString();
    }
}
