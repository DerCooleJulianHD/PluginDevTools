package de.api.devtools.utils.load;

public interface Loadable extends AutoLoadable{

    void load();

    boolean isLoaded();
}

