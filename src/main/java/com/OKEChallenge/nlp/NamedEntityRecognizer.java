package com.OKEChallenge.nlp;

import com.OKEChallenge.SparqlQuery;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import javax.json.Json;
import java.util.*;
import java.util.stream.Collectors;

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

    public List<NamedEntityData> collectTokens(List<CoreLabel> tokens) {
        List<NamedEntityData> results = new LinkedList<>();
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
                String entity = result.toString().trim();
                NamedEntityData ned = new NamedEntityData(entity, oldEntityType, SparqlQuery.executeQuery(entity));
                oldEntityType = currentEntityType;
                result = new StringBuilder();
                if (!ned.entityType.equals("O")) {
                    results.add(ned);
                }
            }
            result.append(token.originalText()).append(" ");
        }

        // Add last entity
        String entity = result.toString().trim();
        NamedEntityData ned = new NamedEntityData(entity, currentEntityType, SparqlQuery.executeQuery(entity));
        if (!ned.entityType.equals("O")) {
            results.add(ned);
        }
        return results;
    }

    public String getResult(String query) {
        StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();
        CoreDocument coreDocument = new CoreDocument(query);
        stanfordCoreNLP.annotate(coreDocument);
        List<CoreLabel> tokens = coreDocument.tokens();
        return collectRelevantTokens(tokens);
    }

    public List<NamedEntityData> getResultsAsMap(String query) {
        StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();
        CoreDocument coreDocument = new CoreDocument(query);
        stanfordCoreNLP.annotate(coreDocument);
        List<CoreLabel> tokens = coreDocument.tokens();
        return collectTokens(tokens);
    }

}