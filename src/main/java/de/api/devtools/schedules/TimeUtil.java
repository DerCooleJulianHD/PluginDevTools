package de.api.devtools.schedules;

//: to calculate into ticks for runnable
public final class TimeUtil {
    public static long ofSeconds(int v) {
        return v * 20L;
    }

    public static long ofMinutes(int v) {
        return ofSeconds(v) * 60;
    }

    public static long ofHours(int v) {
        return ofMinutes(v) * 60;
    }

    public static long ofMilliSeconds(int v) {
        return ofSeconds(v) / 1000;
    }
}
