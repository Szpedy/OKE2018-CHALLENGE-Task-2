package com.OKEChallenge.nlp;

import java.util.List;
import java.util.Map;

public class NamedEntityData {
    public String entity;
    public String entityType;
    public List<Map<String, String>> dbpediaMapping;

    public NamedEntityData(String entity, String entityType, List<Map<String, String>> dbpediaMapping) {
        this.entity = entity;
        this.dbpediaMapping = dbpediaMapping;
        this.entityType = entityType;

        // If cannot find entity type try to get it from dbpedia
        if (this.entityType.equals("O")) {
            if (dbpediaMapping.size() > 0) {
                this.entityType = dbpediaMapping.get(0).get("Label");
            }
        }
    }
}
