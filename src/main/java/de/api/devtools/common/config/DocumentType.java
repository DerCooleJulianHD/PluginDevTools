package de.api.devtools.common.config;

//: which type of file our file has to be
public enum DocumentType {

    JSON(new String[]{".json"}),
    YAML(new String[]{".yml", ".yaml"}),
    TOML(new String[]{".toml"}),
    PROPERTIES(new String[]{".properties"}),
    XML(new String[]{".xml"}),
    TXT(new String[]{".txt"});

    private final String[] values;

    DocumentType(String[] values) {
        this.values = values;
    }

    public String[] ending() {
        return values;
    }
}
