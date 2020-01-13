package com.OKEChallenge.nlp;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.trees.Constituent;
import edu.stanford.nlp.trees.LabeledScoredConstituentFactory;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations;

import java.util.*;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class NamedEntityRecognizer {
    private String query = "";

    public NamedEntityRecognizer(String query) {
        this.query = query;
    }

    private String collectRelevantTokens(List<CoreLabel> tokens) {
        List<CoreLabel> tokensFiltered = tokens
                .stream()
                .filter(token ->
                        !token.get(
                                CoreAnnotations.NamedEntityTagAnnotation.class)
                                .equalsIgnoreCase("O"))
                .collect(Collectors.toList());

        HashMap<String, String> token2Ner = new HashMap<String, String>();

        for (CoreLabel token : tokensFiltered) {
            String ner = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
            token2Ner.put(token.originalText(), ner.toString());
            System.out.println(token.originalText() + " its ner: " + ner);
        }

        GsonBuilder gsonMapBuilder = new GsonBuilder();
 
		Gson gsonObject = gsonMapBuilder.create();
 
		String JSONObject = gsonObject.toJson(token2Ner);
		System.out.println("\nMethod-1: Using Google GSON ==> " + JSONObject);
        System.out.println(tokens);
        System.out.println(token2Ner);

        return JSONObject;
    }

    public String getResult() {
        StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();
        CoreDocument coreDocument = new CoreDocument(query);
        stanfordCoreNLP.annotate(coreDocument);
        List<CoreLabel> tokens = coreDocument.tokens();

        return collectRelevantTokens(tokens).toString();
    }
}
