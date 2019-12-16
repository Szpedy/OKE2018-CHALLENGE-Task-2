package com.OKEChallenge.nlp.examples;

import com.OKEChallenge.nlp.Pipeline;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.HashMap;
import java.util.List;

public class PartsOfSpeechRecognizer {

    private static String getPairsOfPos() {
        return "CC, Coordinating conjunction\n" +
                "CD, Cardinal number\n" +
                "DT, Determiner\n" +
                "EX, Existential there\n" +
                "FW, Foreign word\n" +
                "IN, Preposition or subordinating conjunction\n" +
                "JJ, Adjective\n" +
                "JJR, Adjective, comparative\n" +
                "JJS, Adjective, superlative\n" +
                "LS, List item marker\n" +
                "MD, Modal\n" +
                "NN, Noun, singular or mass\n" +
                "NNS, Noun, plural\n" +
                "NNP, Proper noun, singular\n" +
                "NNPS, Proper noun, plural\n" +
                "PDT, Predeterminer\n" +
                "POS, Possessive ending\n" +
                "PRP, Personal pronoun\n" +
                "PRP$, Possessive pronoun\n" +
                "RB, Adverb\n" +
                "RBR, Adverb, comparative\n" +
                "RBS, Adverb, superlative\n" +
                "RP, Particle\n" +
                "SYM, Symbol\n" +
                "TO, to\n" +
                "UH, Interjection\n" +
                "VB, Verb, base form\n" +
                "VBD, Verb, past tense\n" +
                "VBG, Verb, gerund or present participle\n" +
                "VBN, Verb, past participle\n" +
                "VBP, Verb, non3rd person singular present\n" +
                "VBZ, Verb, 3rd person singular present\n" +
                "WDT, WhDdeterminer\n" +
                "WP, Whpronoun\n" +
                "WP$, Possessive whpronoun\n" +
                "WRB, Whadverb\n";
    }

    private static HashMap<String, String> getPOS2Str() {
        HashMap<String, String> pos2String = new HashMap<String, String>();

        for (String x : getPairsOfPos().split("\n")) {
            String[] leftNRight = x.split(", ");
            String left = leftNRight[0].trim();
            String right = leftNRight[1].trim();

            pos2String.put(left, right);
        }

        return pos2String;
    }

    public static void main(String[] args) {
        HashMap<String, String> pos2String = getPOS2Str();

        StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();
        String text = "Patrick Romaniack has created this amazingly sophisticated app for the OKE Challenge!";

        CoreDocument coreDocument = new CoreDocument(text);
        stanfordCoreNLP.annotate(coreDocument);
        List<CoreLabel> coreLabelList = coreDocument.tokens();

        for (CoreLabel label : coreLabelList) {
            String partOfSpeech = label.get(CoreAnnotations.PartOfSpeechAnnotation.class);
            String humanReadablePOS = pos2String.get(partOfSpeech);
            System.out.println(label.originalText() + " = " + partOfSpeech + " -> " + humanReadablePOS);
        }
    }
}
