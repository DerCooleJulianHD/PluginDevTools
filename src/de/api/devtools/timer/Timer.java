package de.api.devtools.timer;

public abstract class Timer extends Runnable {

    protected final long delay;
    protected final long period;

    public Timer(long delay, long period) {
        this.delay = delay;
        this.period = period;

        this.runTaskTimer(delay, period);
    }

    public final long getDelay() {
        return delay;
    }

    public final long getPeriod() {
        return period;
    }
}
