package com.OKEChallenge;

import org.junit.Test;

import java.util.List;

public class SparqlQueryTest {

    @Test
    public void donaldTrumpShouldBePerson() {
        SparqlQuery sparqlQuery = new SparqlQuery();
        List<String> types = sparqlQuery.executeQuery("Donald_Trump");
        types.forEach(System.out::println);
        assert ( types.get(0).equals("person"));
    }
}
