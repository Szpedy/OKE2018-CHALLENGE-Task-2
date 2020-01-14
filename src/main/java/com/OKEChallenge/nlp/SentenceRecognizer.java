package com.OKEChallenge.nlp;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import java.util.List;

public class SentenceRecognizer {

    public List<CoreMap> getSentences(String text) {
        StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();
        Annotation document = new Annotation(text);
        stanfordCoreNLP.annotate(document);
        return document.get(CoreAnnotations.SentencesAnnotation.class);
    }
}
