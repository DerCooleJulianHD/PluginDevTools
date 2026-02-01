package de.api.devtools.scoreboard.util;

public enum Criteria {

    DUMMY("dummy");

    private final String id;

    Criteria(String id) {
        this.id = id;
    }

    public final String getId() {
        return id;
    }
}
