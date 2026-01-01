package de.api.devtools.timer;

import de.api.devtools.plugin.SpigotPlugin;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

public abstract class Runnable {

    private int runningState = -1;

    private final SpigotPlugin plugin = SpigotPlugin.getInstance();

    public Runnable() {
    }

    protected abstract void run();

    public final void cancel() {
        Bukkit.getScheduler().cancelTask(runningState);
    }

    public final void runTaskTimer(long delay, long period) {
        runTask(Bukkit.getScheduler().runTaskTimer(plugin, this::run, delay, period));
    }

    public final void runTaskLater(long delay) {
        runTask(Bukkit.getScheduler().runTaskLater(plugin, this::run, delay));
    }

    private void runTask(BukkitTask task) {
        if (isRunning()) throw new IllegalStateException("Already Running as: " + task.getTaskId());

        this.runningState = task.getTaskId();
    }

    public final boolean isRunning() {
        return runningState != -1;
    }
}
