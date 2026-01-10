package de.api.devtools.schedules;

public abstract class Scheduler implements Runnable {

    private int taskId = -1;
    private final long delay, period;
    private static Scheduler instance;

    public Scheduler(long delay, long period) {
        instance = this;
        this.delay = delay;
        this.period = period;
        if (getAutoStart()) start();
    }

    public Scheduler(long period) {
        this(0L, period);
    }

    public final long getPeriod() {
        return period;
    }

    public final long getDelay() {
        return delay;
    }

    public static Scheduler getInstance() {
        return instance;
    }

    @Override
    public int getTaskId() {
        return taskId;
    }

    @Override
    public void setTaskId(int id) {
        this.taskId = id;
    }
}
