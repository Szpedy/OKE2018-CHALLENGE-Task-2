package com.OKEChallenge.nlp;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.List;

public class SentenceRecognizer {
    public static void main(String[] args) {
        StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();
        String text = "Patric Romaniack has created this amazingly sophisticated app for the OKE Challenge! " +
                "And yet, another one";

        CoreDocument coreDocument = new CoreDocument(text);
        stanfordCoreNLP.annotate(coreDocument);
        List<CoreSentence> coreSentenceList = coreDocument.sentences();

        for (CoreSentence sentence: coreSentenceList)
            System.out.println(sentence);
    }
}
