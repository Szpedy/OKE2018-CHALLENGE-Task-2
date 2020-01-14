package com.OKEChallenge.nlp;

public enum EntityCategories {
    PERSON("Person"),
    CITY("City"),
    LOCATION("Location"),
    COUNTRY("Country"),
    TITLE("Title"),
    ORGANIZATION("Organizaion"),
    DATE("Date"),
    MONEY("Money"),
    PERCENT("Percent");

    private String cat = "";
    EntityCategories(String cat) {
        this.cat = cat;
    }

    public String getName() {
        return cat;
    }
}
