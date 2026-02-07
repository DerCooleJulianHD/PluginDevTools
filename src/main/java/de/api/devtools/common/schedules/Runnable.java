package de.api.devtools.common.schedules;

import de.api.devtools.common.plugin.MinecraftPlugin;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import javax.annotation.Nonnull;

public abstract class Runnable {

    @Nonnull protected final MinecraftPlugin plugin;

    protected final long delay;
    protected final long period;

    private boolean autoStart = getClass().isAnnotationPresent(AutoStart.class);

    private BukkitTask task;

    public Runnable(@Nonnull MinecraftPlugin plugin, long delay, long period) {
        this.plugin = plugin;
        this.delay = delay;
        this.period = period;
        if (getAutoStart()) runTask();
    }

    public Runnable(@Nonnull MinecraftPlugin plugin, long period) {
        this(plugin, 0L, period);
    }

    public abstract void run();

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

    public final void runTask() {
        if (this.task != null)
            throw new IllegalStateException("Already scheduled as " + this.task.getTaskId() + "!");

        final BukkitTask task = Bukkit.getScheduler().runTaskTimer(plugin, this::run, delay, period);

        this.setTask(task);
    }

    private void setTask(BukkitTask task) {
        this.task = task;
    }

    public final void cancel() {
        if (task == null)
            throw new IllegalStateException("Not scheduled yet!");

        Bukkit.getScheduler().cancelTask(task.getTaskId());

        setTask(null);
    }

    public final void cancelIf(boolean expression) {
        if (expression) cancel();
    }

    public void onStop() {}

    public void onStart() {}

    public final boolean getAutoStart() {
        return autoStart;
    }

    public final void setAutoStart(boolean b) {
        this.autoStart = b;
    }

    @Nonnull public MinecraftPlugin getPlugin() {
        return plugin;
    }
}
