package com.OKEChallenge;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.RDFNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SparqlQuery {
    private static final String ENDPOINT = "https://dbpedia.org/sparql";
    private static final String PREFIXES = new String(
            "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
                    "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                    "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
                    "PREFIX dbp: <http://dbpedia.org/property/>\n" +
                    "PREFIX res: <http://dbpedia.org/resource/>\n"
    );


    /**
     * Query that returns type uri and label of the resource
     * */
    private String getQueryStr(String resource) {
        return PREFIXES + "select distinct ?type ?label {\n" +
                "   res:" + resource + " rdf:type ?type.\n" +
                "   ?type rdfs:label ?label .\n" +
                "   filter(langMatches(lang(?label),\"EN\") && REGEX ( STR (?type), \"http://dbpedia.org/ontology/\", \"i\" ) )\n" +
                "}\n" +
                "limit 10";
    }

    /**
     * Sparql query executor
     *
     * @param resource eg. Donald_Trump all white spaces should be an underscore
     * @return list of types from db
     */
    public List<Map<String, String>> executeQuery(String resource) {
        List<Map<String, String>> types = new LinkedList<>();
        Query query = QueryFactory.create(getQueryStr(resource));
        QueryExecution exec = QueryExecutionFactory.sparqlService(ENDPOINT, query);
        ResultSet results = exec.execSelect();
        while (results.hasNext()) {
            HashMap<String, String> row = new HashMap<>();
            QuerySolution qSolution = results.nextSolution();
            row.put("Label",  qSolution.get("label").toString().replace("@en", ""));
            row.put("Type Uri", qSolution.get("type").toString());
            row.put("Resource Uri", "http://dbpedia.org/resource/" + resource);
            types.add(row);
        }
        return types;
    }
}
