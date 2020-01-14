package com.OKEChallenge.nlp;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.*;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class NamedEntityRecognizer {

    private String collectRelevantTokens(List<CoreLabel> tokens) {
        List<CoreLabel> tokensFiltered = tokens
                .stream()
                .filter(token ->
                        !token.get(
                                CoreAnnotations.NamedEntityTagAnnotation.class)
                                .equalsIgnoreCase("O"))
                .collect(Collectors.toList());

        HashMap<String, String> token2Ner = new HashMap<>();

        for (CoreLabel token : tokensFiltered) {
            String ner = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
            token2Ner.put(token.originalText(), ner);
        }

        GsonBuilder gsonMapBuilder = new GsonBuilder();
        Gson gsonObject = gsonMapBuilder.create();
        String JSONObject = gsonObject.toJson(token2Ner);
        System.out.println("\nMethod-1: Using Google GSON ==> " + JSONObject);
        System.out.println(tokens);
        System.out.println(token2Ner);
        return JSONObject;
    }

    public HashMap<String, String> collectTokens(List<CoreLabel> tokens) {
        HashMap<String, String> token2Ner = new HashMap<>();
        String currentEntityType = "";
        String oldEntityType = "";
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tokens.size(); i++) {
            CoreLabel token = tokens.get(i);
            currentEntityType = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
            if (i == 0) {
                oldEntityType = currentEntityType;
            }
            if (!currentEntityType.equals(oldEntityType)) {
                token2Ner.put(result.toString().trim(), oldEntityType);
                oldEntityType = currentEntityType;
                result = new StringBuilder();
            }
            result.append(token.originalText()).append(" ");
        }
        token2Ner.put(result.toString().trim(), currentEntityType);
        return token2Ner;
    }

    public String getResult(String query) {
        StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();
        CoreDocument coreDocument = new CoreDocument(query);
        stanfordCoreNLP.annotate(coreDocument);
        List<CoreLabel> tokens = coreDocument.tokens();
        return collectRelevantTokens(tokens);
    }

    public HashMap<String, String> getResultsAsMap(String query) {
        StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();
        CoreDocument coreDocument = new CoreDocument(query);
        stanfordCoreNLP.annotate(coreDocument);
        List<CoreLabel> tokens = coreDocument.tokens();
        return collectTokens(tokens);
    }

}