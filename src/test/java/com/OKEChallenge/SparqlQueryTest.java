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

    @Test
    public void FbiShouldBeOrganization() {
        SparqlQuery sparqlQuery = new SparqlQuery();
        List<Map<String, String>> types = sparqlQuery.executeQuery("FBI");
        for (Map<String, String> row : types) {
            System.out.println(row.keySet());
            System.out.println(row.values());
        }
//         Cia resource redirects to http://dbpedia.org/page/Central_Intelligence_Agency
//         Cia resource does not have type. Type is available after redirection
//         SELECT * WHERE {
//           <http://dbpedia.org/resource/CIA> ?p ?o
//        }
//                assert ( types.get(0).equals("person"));
    }

}
