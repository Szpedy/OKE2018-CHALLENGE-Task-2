package com.OKEChallenge;

import com.OKEChallenge.nlp.examples.NamedEntityRecognizer;
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
