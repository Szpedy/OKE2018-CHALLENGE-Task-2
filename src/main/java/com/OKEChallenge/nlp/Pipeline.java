package com.OKEChallenge.nlp;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

public class Pipeline {
    private static Properties properties;
    /**
     * propertiesNames options:
     * - tokenize     - tokenization ("ala ma kota!" -> ["ala", "ma", "kota", "!"])
     * - ssplit       - sentence split ("ala ma kota! a ty nie" -> ["ala ma kota!", "a ty nie"])
     * - pos          - parts of speech ("ala ma kota! a ty nie" -> ["ala ma kota!", "a ty nie"])
     * - lemma        - lemmatization - approach to getting a proper core of the word in its base from dicts, etc.
     * - ner          - named-entity recognizer - annotates words to some recognizable entities
     * - parse        - parser is indirectly used in the sentiment analysis
     * - sentiment    - for sentiment analysis
     */

    private static String propertiesName = "tokenize, ssplit, pos, lemma, ner, parse, sentiment";
    private static StanfordCoreNLP stanfordCoreNLP;
    public static List<String> stopwords;

    private Pipeline() {
    }

    static {
        properties = new Properties();
        properties.setProperty("annotators", propertiesName);
    }

    public static StanfordCoreNLP getPipeline() {
        if (stanfordCoreNLP == null) {
            stanfordCoreNLP = new StanfordCoreNLP(properties);
            loadStopwords();
        }
        return stanfordCoreNLP;
    }

    private static void loadStopwords() {
        try {
            stopwords = Files.readAllLines(Paths.get("english_stopwords.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
