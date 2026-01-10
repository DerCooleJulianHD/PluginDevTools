package de.api.devtools.schedules;

public class Timer extends Runnable implements TimeState {

    private int time;

    public Timer(int start, long delay, long period) {
        super(delay, period);
        this.time = start;
    }

    @Override
    public void run() {
        if (!isRunning()) return;
        this.setTime(time + 1);
    }

    @Override
    public final int getTime() {
        return time;
    }

    @Override
    public final void setTime(int time) {
        this.time = time;
    }

    @Override
    public void onTimeChange() {}

    @Override
    public void onStop() {}

    @Override
    public void onStart() {}
}
