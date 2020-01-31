package com.OKEChallenge.nlp;

import edu.stanford.nlp.trees.Constituent;
import edu.stanford.nlp.trees.LabeledScoredConstituentFactory;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NounPhraseExtractor {

    public List<String> getNpsFromText(CoreMap sen) {
        List<String> nounPhrases = new LinkedList<>();
        Tree tree = sen.get(TreeCoreAnnotations.TreeAnnotation.class);
        Set<Constituent> treeConstituents = tree.constituents(new LabeledScoredConstituentFactory());
        System.out.println(tree);
        for (Constituent constituent : treeConstituents) {
            if (constituent.label() != null && constituent.label().toString().equals("NP")) {
                String nounPhrase = getStringFromTreeList(tree.getLeaves().subList(constituent.start(), constituent.end() + 1));
                nounPhrases.add(removeStopWords(nounPhrase));
            }
        }
        return new ArrayList<>(new HashSet<>(nounPhrases));
    }

    private String getStringFromTreeList(List<Tree> treeList) {
        return treeList.toString()
                .replace(",", "")
                .replace("[", "")
                .replace("]", "")
                .trim();
    }

    private String removeStopWords(String sentence) {
        ArrayList<String> allWords = Stream
                .of(sentence.split(" "))
                .collect(Collectors.toCollection(ArrayList::new));

        for (int i = 0; i < allWords.size(); i++) {
            if (Pipeline.stopwords.contains(allWords.get(i).toLowerCase())){
                allWords.remove(allWords.get(i));
            }
        }
        return String.join(" ", allWords);
    }

}
