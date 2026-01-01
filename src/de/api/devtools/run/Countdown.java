package de.api.devtools.run;

//: type of timer that counts down to zero and end
public class Countdown extends Timer {

    public Countdown(int value, long delay, long period) {
        super(Action.DECREASE_VALUE, value, 0, delay, period);
    }

    public Countdown(int value, long period) {
        this(value, 0, period);
    }
}
