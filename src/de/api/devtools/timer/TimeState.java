package de.api.devtools.timer;

public interface TimeState {

    int getTime();

    void onTimeChange();

    void setTime(int time);

    default boolean hasValue(int time) {
        return getTime() == time;
    }
}
