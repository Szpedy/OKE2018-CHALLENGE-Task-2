package com.OKEChallenge;

import org.junit.Test;

import java.util.List;
import java.util.Map;

public class SparqlQueryTest {

    @Test
    public void donaldTrumpShouldBePerson() {
        SparqlQuery sparqlQuery = new SparqlQuery();
        List<Map<String, String>> types = sparqlQuery.executeQuery("Donald_Trump");
        for (Map<String, String> row : types) {
            System.out.println(row.keySet());
            System.out.println(row.values());
        }
        assert (types.get(0).get("Label").equals("person"));
    }
}
