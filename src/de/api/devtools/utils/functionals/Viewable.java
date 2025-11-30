package de.api.devtools.utils.functionals;

public interface Viewable<T> {

    // returns a specific player which is viewing an inventory
    T getViewer();

    // returns true when player is a viewer in that inventory
    default boolean isViewer(T t) {
        return getViewer() == t;
    }

    void setViewer(T t);
}
