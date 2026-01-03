package de.api.devtools.timer;

import de.api.devtools.run.Scheduler;

//: type of timer that counts down to zero and end
public class Countdown extends Scheduler implements TimeState {

    protected int time;
    protected final int end = 0;

    public Countdown(int start, long delay, long period) {
        super(delay, period);
        this.time = start;
    }

    public Countdown(int start, long period) {
        this(start, 0, period);
    }

    @Override
    protected void run() {
        time--;

        if (time == end)
            cancel();
    }

    @Override
    public void onTimeChange() {}

    @Override
    public int getTime() {
        return time;
    }

    @Override
    public void setTime(int time) {
        this.time = time;

        this.onTimeChange();
    }
}
