package de.api.devtools.run;

import de.api.devtools.utils.TextColor;
import org.bukkit.Bukkit;

//: type of runnable that executes code within periods.
public abstract class Scheduler extends Runnable {

    protected final long delay;
    protected final long period;

    public Scheduler(long delay, long period) {
        this.delay = delay;
        this.period = period;
        if (isAutoStart()) this.start();
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

    public final boolean isAutoStart() {
        return getClass().isAnnotationPresent(AutoStart.class);
    }

    public final void start() {
        this.runTaskTimer(delay, period);
        onStartTask();
    }

    public final void start(String message) {
        start();
        Bukkit.broadcastMessage(TextColor.colorize(message));
    }
}
