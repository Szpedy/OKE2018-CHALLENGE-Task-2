package com.OKEChallenge.nlp.examples;

import com.OKEChallenge.nlp.Pipeline;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.trees.Constituent;
import edu.stanford.nlp.trees.LabeledScoredConstituentFactory;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations;

import java.util.List;
import java.util.Set;

public class NamedEntityRecognizer {

    public void getNpsFromText(String text) {
        StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();

        // Trying to get NP from sentence
        Annotation annotation = new Annotation(text);
        stanfordCoreNLP.annotate(annotation);
        Tree tree = annotation.get(CoreAnnotations.SentencesAnnotation.class).get(0).get(TreeCoreAnnotations.TreeAnnotation.class);
        System.out.println(tree);
        Set<Constituent> treeConstituents = tree.constituents(new LabeledScoredConstituentFactory());
        for (Constituent constituent : treeConstituents) {
            if (constituent.label() != null && constituent.label().toString().equals("NP")) {
                System.err.println("found constituent: " + constituent.toString());
                System.err.println(tree.getLeaves().subList(constituent.start(), constituent.end() + 1));
            }
        }
    }


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
