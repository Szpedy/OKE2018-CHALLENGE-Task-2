package com.OKEChallenge.nlp.examples;

import com.OKEChallenge.nlp.Pipeline;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.List;

public class NamedEntityRecognizer {
    public static void main(String[] args) {



        StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();
        String text = "Patrick Romaniack has created this amazingly sophisticated app for the OKE Challenge! " +
                "I am Albert and we're friends living in Poznań.";

        CoreDocument coreDocument = new CoreDocument(text);
        stanfordCoreNLP.annotate(coreDocument);
        List<CoreLabel> coreLabelList = coreDocument.tokens();

        for (CoreLabel label : coreLabelList) {
            String ner = label.get(CoreAnnotations.NamedEntityTagAnnotation.class);
            System.out.println(label.originalText() + " its ner: " + ner);
        }
    }
}
