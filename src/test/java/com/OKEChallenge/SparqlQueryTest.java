package com.OKEChallenge;

import com.OKEChallenge.nlp.examples.NamedEntityRecognizer;
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
        List<String> types = sparqlQuery.executeQuery("Cia");
        types.forEach(System.out::println);
        // Cia resource redirects to http://dbpedia.org/page/Central_Intelligence_Agency
        // Cia resource does not have type. Type is available after redirection
        // SELECT * WHERE {
        //   <http://dbpedia.org/resource/CIA> ?p ?o
        //}
        //        assert ( types.get(0).equals("person"));
    }

    @Test
    public void iShouldGetAllNpsFromText() {
        String text = "The small red car turned very quickly around the corner.";
        new NamedEntityRecognizer().getNpsFromText(text);
    }
}
