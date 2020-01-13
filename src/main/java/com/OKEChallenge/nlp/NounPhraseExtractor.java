package com.OKEChallenge.nlp;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.trees.Constituent;
import edu.stanford.nlp.trees.LabeledScoredConstituentFactory;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NounPhraseExtractor {

    public List<String> getNpsFromText(String sentence) {
        List<String> nounPhrases = new LinkedList<>();
        StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();
        Annotation annotation = new Annotation(sentence);
        stanfordCoreNLP.annotate(annotation);
        Tree tree = annotation.get(CoreAnnotations.SentencesAnnotation.class).get(0).get(TreeCoreAnnotations.TreeAnnotation.class);
        Set<Constituent> treeConstituents = tree.constituents(new LabeledScoredConstituentFactory());
        System.out.println(tree);
        for (Constituent constituent : treeConstituents) {
            if (constituent.label() != null && constituent.label().toString().equals("NP")) {
                String nounPhrase = getStringFromTreeList(tree.getLeaves().subList(constituent.start(), constituent.end() + 1));
                nounPhrases.add(removeStopWords(nounPhrase));
            }
        }
        return nounPhrases;
    }

    private String getStringFromTreeList(List<Tree> treeList) {
        return treeList.toString()
                .replace(",", "")
                .replace("[", "")
                .replace("]", "")
                .trim();
    }

    private String removeStopWords(String sentence) {
        ArrayList<String> allWords = Stream.of(sentence.split(" "))
                .collect(Collectors.toCollection(ArrayList::new));
        allWords.removeAll(Pipeline.stopwords);
        return String.join(" ", allWords);
    }

}
