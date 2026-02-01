package de.api.devtools.scoreboard;

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
