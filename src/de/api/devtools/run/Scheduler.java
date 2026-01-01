package de.api.devtools.run;

//: type of runnable that executes code within periods.
public abstract class Scheduler extends Runnable {

    protected final long delay;
    protected final long period;

    public Scheduler(long delay, long period) {
        this.delay = delay;
        this.period = period;
        this.runTaskTimer(delay, period);
        this.onStartTask();
    }

    public Scheduler(long period) {
        this(0, period);
    }

    public void onStartTask() {}

    public long getDelay() {
        return delay;
    }

    public long getPeriod() {
        return period;
    }
}
