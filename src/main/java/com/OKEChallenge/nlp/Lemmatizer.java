package com.OKEChallenge.nlp;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.List;

public class Lemmatizer {
    public static void main(String[] args) {
        StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();
        String text = "Patrick Romaniack has created this amazingly sophisticated app for the OKE Challenge!";

        CoreDocument coreDocument = new CoreDocument(text);
        stanfordCoreNLP.annotate(coreDocument);
        List<CoreLabel> coreLabelList = coreDocument.tokens();

        for (CoreLabel label : coreLabelList)
            System.out.println(label.originalText() + " and its lemma: " + label.lemma());
    }
}
