package de.api.devtools.load;

public interface Loadable extends AutoLoadable{

    void load();

    boolean isLoaded();
}

