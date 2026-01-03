package de.api.devtools.timer;

import de.api.devtools.run.Scheduler;

//: type of timer that counts down to zero and end
public class Countdown extends Scheduler implements TimeState {

    private final Countdown instance;

    protected int timeLeft;
    protected final int end = 0;

    public Countdown(int start, long delay, long period) {
        super(delay, period);
        this.timeLeft = start;

        instance = this;
    }

    public Countdown(int start, long period) {
        this(start, 0, period);
    }

    @Override
    protected void run() {
        timeLeft--;

        if (timeLeft == end)
            cancel();
    }

    @Override
    public void onTimeChange() {}

    @Override
    public int getTime() {
        return timeLeft;
    }

    @Override
    public void setTime(int time) {
        this.timeLeft = time;

        this.onTimeChange();
    }

    public Countdown getInstance() {
        return instance;
    }
}
