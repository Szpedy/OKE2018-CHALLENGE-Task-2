package com.OKEChallenge.nlp;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.List;

public class SenitmentAnalyzer {
    public static void main(String[] args) {
        StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();
        String text = "Patrick Romaniack has created this amazingly sophisticated app for the OKE Challenge! I really love it so far.";

        CoreDocument coreDocument = new CoreDocument(text);
        stanfordCoreNLP.annotate(coreDocument);
        List<CoreSentence> coreSentences = coreDocument.sentences();

        for (CoreSentence sentence : coreSentences) {
            String sentiment = sentence.sentiment();
            System.out.println("Sentiment of the sentence: " + sentence + " is -> " + sentiment);
        }
    }
}
