package com.OKEChallenge;

import org.apache.jena.query.*;

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
     */
    @Deprecated
    private String _getQueryStr(String resource) {
        return PREFIXES + "select distinct ?type ?label {\n" +
                "   res:" + resource + " rdf:type ?type.\n" +
                "   ?type rdfs:label ?label .\n" +
                "   filter(langMatches(lang(?label),\"EN\") && REGEX ( STR (?type), \"http://dbpedia.org/ontology/\", \"i\" ) )\n" +
                "}\n" +
                "limit 10";
    }

    /**
     * Query that returns type uri and label of the resource
     */
    private static String getQueryStr(String resource) {
        return PREFIXES + " SELECT distinct * WHERE {\n" +
                "  OPTIONAL { res:" + resource + " dbo:wikiPageRedirects ?redirectsTo.\n" +
                "             ?redirectsTo rdf:type ?type. \n" +
                "             ?type rdfs:label ?label .\n" +
                "             filter(langMatches(lang(?label),\"EN\") && REGEX ( STR (?type), \"http://dbpedia.org/ontology/\", \"i\" ) )\n" +
                "}\n" +
                "  OPTIONAL { res:" + resource + " rdf:type ?type.\n" +
                "             ?type rdfs:label ?label .\n" +
                "             filter(langMatches(lang(?label),\"EN\") && REGEX ( STR (?type), \"http://dbpedia.org/ontology/\", \"i\" ) )\n" +
                "           }\n" +
                "}\n" +
                "LIMIT 10";
    }


    /**
     * Sparql query executor
     *
     * @param resource eg. Donald_Trump all white spaces should be an underscore
     * @return list of types from db
     */

    public static List<Map<String, String>> executeQuery(String resource) {
        List<Map<String, String>> results = new LinkedList<>();
        Query query = QueryFactory.create(getQueryStr(resource));
        QueryExecution exec = QueryExecutionFactory.sparqlService(ENDPOINT, query);
        ResultSet rs = exec.execSelect();
        while (rs.hasNext()) {
            HashMap<String, String> row = new HashMap<>();
            QuerySolution qSolution = rs.nextSolution();
            if (qSolution.get("label") != null) {
                row.put("Label", qSolution.get("label").toString().replace("@en", ""));
                row.put("Type Uri", qSolution.get("type").toString());
                row.put("Resource Uri", "http://dbpedia.org/resource/" + resource);
                results.add(row);
            }
        }
        return results;
    }
}
