package de.api.devtools.run;

import de.api.devtools.plugin.SpigotPlugin;
import de.api.devtools.utils.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

//: base class for all timed actions
public abstract class Runnable {

    private int runningState = -1;

    private final SpigotPlugin plugin = SpigotPlugin.getInstance();

    public Runnable() {
    }

    protected abstract void run();

    public final void cancel() {
        Bukkit.getScheduler().cancelTask(runningState);

        onCancelTask();
    }

    public final void cancel(String broadcast) {
        cancel();

        Bukkit.broadcastMessage(TextColor.colorize(broadcast));
    }

    protected final void runTaskTimer(long delay, long period) {
        runTask(Bukkit.getScheduler().runTaskTimer(plugin, this::run, delay, period));
    }

    public static void runTaskLater(SpigotPlugin plugin, long delay, java.lang.Runnable run) {
        Bukkit.getScheduler().runTaskLater(plugin, run, delay);
    }

    private void runTask(BukkitTask task) {
        if (isRunning()) throw new IllegalStateException("Already Running as: " + task.getTaskId());

        this.runningState = task.getTaskId();
    }

    public final boolean isRunning() {
        return runningState != -1;
    }

    public void onCancelTask() {}
}
