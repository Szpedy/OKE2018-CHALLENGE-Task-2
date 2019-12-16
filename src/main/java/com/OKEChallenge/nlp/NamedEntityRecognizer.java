package com.OKEChallenge.nlp;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.*;
import java.util.stream.Collectors;

public class NamedEntityRecognizer {
//    private EntityCategories ec = null;
//
//    public NamedEntityRecognizer(final EntityCategories ec) {
//        this.ec = ec;
//    }

    private List<String> collectRelevantTokens(List<CoreLabel> tokens) {
        // TODO: fix this to work with EntityCategories
        //       + update them to contain all the categories from CoreNLP

        List<String> interestingCategories = Arrays.asList("PERSON", "COUNTRY");
        return tokens
                .stream()
                .filter(token -> interestingCategories.contains(token.get(CoreAnnotations.NamedEntityTagAnnotation.class)))
                .map(CoreLabel::originalText)
                .collect(Collectors.toList());
    }

    public Set<String> getResult(String query) {
        HashMap<String, String> result = new HashMap<String, String>();

        StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();
        CoreDocument coreDocument = new CoreDocument(query);
        stanfordCoreNLP.annotate(coreDocument);
        List<CoreLabel> tokens = coreDocument.tokens();

        return new HashSet<String>(collectRelevantTokens(tokens));
    }

}
