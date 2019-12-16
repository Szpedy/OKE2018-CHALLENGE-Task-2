package com.OKEChallenge.nlp;

public enum EntityCategories {
    PERSON("Person"),
    CITY("City"),
    LOCATION("Location"),
    COUNTRY("Country");

    private String cat = "";
    EntityCategories(String cat) {
        this.cat = cat;
    }

    public String getName() {
        return cat;
    }
}
