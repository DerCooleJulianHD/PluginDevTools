package de.api.devtools.timer;

import de.api.devtools.run.Scheduler;

//: type of scheduler to count time and execute code on time change
public class Timer extends Scheduler implements TimeState {

    protected int time;
    protected int end;

    public Timer(int start, int end, long delay, long period) {
        super(delay, period);
        this.time = start;
        this.end = end;
    }

    public Timer(int start, int end, long period) {
        this(start, end, 0, period);
    }

    public Timer(int start, long period) {
        this(start, Integer.MAX_VALUE, period);
    }

    @Override
    protected void run() {
        time++;

        if (time == end)
            cancel("Timer has reached the end.");
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
