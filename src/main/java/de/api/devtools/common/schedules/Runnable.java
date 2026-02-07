package de.api.devtools.common.schedules;

import de.api.devtools.common.plugin.SpigotPlugin;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

public abstract class Runnable {

    private static Runnable instance;

    private final long delay;
    private final long period;

    private boolean autoStart = getClass().isAnnotationPresent(AutoStart.class);

    private BukkitTask task;

    public Runnable(long delay, long period) {
        instance = this;
        this.delay = delay;
        this.period = period;
        if (getAutoStart()) runTask();
    }

    public Runnable(long period) {
        this(0L, period);
    }

    public abstract void run();

    public static Runnable getInstance() {
        return instance;
    }

    public final long getPeriod() {
        return period;
    }

    public final long getDelay() {
        return delay;
    }

    public final boolean isDelayed() {
        return getDelay() > 0;
    }

    public final boolean isRunning() {
        return task != null && (!task.isCancelled());
    }

    public void runTask() {
        if (this.task != null)
            throw new IllegalStateException("Already scheduled as " + this.task.getTaskId() + "!");

        final BukkitTask task = Bukkit.getScheduler().runTaskTimer(SpigotPlugin.getInstance(), this::run, delay, period);

        this.setTask(task);
    }

    private void setTask(BukkitTask task) {
        this.task = task;
    }

    public void cancel() {
        if (task == null)
            throw new IllegalStateException("Not scheduled yet!");

        Bukkit.getScheduler().cancelTask(task.getTaskId());

        setTask(null);
    }

    public final void cancelIf(boolean expression) {
        if (expression) cancel();
    }

    public abstract void onStop();

    public abstract void onStart();

    public final boolean getAutoStart() {
        return autoStart;
    }

    public void setAutoStart(boolean b) {
        this.autoStart = b;
    }
}
