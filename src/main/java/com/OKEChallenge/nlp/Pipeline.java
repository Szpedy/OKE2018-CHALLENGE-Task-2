package com.OKEChallenge.nlp;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.Properties;

public class Pipeline {
    private static Properties properties;

    /**
     * propertiesNames options:
     *  - tokenize     - tokenization ("ala ma kota!" -> ["ala", "ma", "kota", "!"])
     *  - ssplit       - sentence split ("ala ma kota! a ty nie" -> ["ala ma kota!", "a ty nie"])
     *  - pos          - parts of speech ("ala ma kota! a ty nie" -> ["ala ma kota!", "a ty nie"])
     */

    private static String propertiesName = "tokenize, ssplit, pos";
    private static StanfordCoreNLP stanfordCoreNLP;

    private Pipeline() { }

    static {
        properties = new Properties();
        properties.setProperty("annotators", propertiesName);
    }

    public static StanfordCoreNLP getPipeline() {
        if (stanfordCoreNLP == null)
            stanfordCoreNLP = new StanfordCoreNLP(properties);
        return stanfordCoreNLP;
    }
}
