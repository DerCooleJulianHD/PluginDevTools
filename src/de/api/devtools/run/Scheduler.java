package de.api.devtools.run;

//: type of runnable that executes code within periods
public abstract class Scheduler extends Runnable {

    protected final long delay;
    protected final long period;

    public Scheduler(long delay, long period) {
        this.delay = delay;
        this.period = period;

        if (isAutoStart()) {
            this.runTaskTimer(delay, period);
            this.onStart();
        }
    }

    public Scheduler(long period) {
        this(0, period);
    }

    public void onStart() {}

    public final long getDelay() {
        return delay;
    }

    public final long getPeriod() {
        return period;
    }

    public final boolean isDelayed() {
        return getDelay() > 0;
    }
}
