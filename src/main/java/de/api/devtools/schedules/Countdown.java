package de.api.devtools.schedules;

public class Countdown extends Timer {

    public Countdown(int start, long delay) {
        super(start, delay, TimeUtil.ofSeconds(1));
    }

    public Countdown(int start) {
        this(start, 0L);
    }

    @Override
    public final void run() {
        if (!isRunning()) return;
        this.setTime(getTime() - 1);
        this.cancelIf(getTime() <= 0);
    }
}
