package de.api.devtools.schedules;

public class Countdown extends Runnable implements TimeState {

    private int timeLeft;

    public Countdown(int start, long period) {
        super(period);
        this.timeLeft = start;
    }

    public Countdown(int start) {
        this(start, TimeUtil.ofSeconds(1));
    }

    @Override
    public final void run() {
        if (!isRunning()) return;
        this.setTime(getTime() - 1);
        this.cancelIf(getTime() <= 0);
    }

    @Override
    public int getTime() {
        return timeLeft;
    }

    @Override
    public void setTime(int time) {
        this.timeLeft = time;
    }

    @Override
    public void onTimeChange() {}

    @Override
    public void onStop() {}

    @Override
    public void onStart() {}
}
