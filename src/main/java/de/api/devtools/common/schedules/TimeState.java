package de.api.devtools.common.schedules;

public interface TimeState {

    int getTime();

    void onTimeChange();

    void setTime(int time);

    default boolean hasValue(int time) {
        return getTime() == time;
    }
}
