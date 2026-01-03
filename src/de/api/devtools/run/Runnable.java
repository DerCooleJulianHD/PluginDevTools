package de.api.devtools.run;

import de.api.devtools.plugin.SpigotPlugin;
import de.api.devtools.utils.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

//: base class for all timed actions
public abstract class Runnable {

    private final SpigotPlugin plugin = SpigotPlugin.getInstance();

    private int state = -1;

    public Runnable() {
    }

    protected abstract void run();

    public final void cancel() {
        Bukkit.getScheduler().cancelTask(state);

        this.onStop();
    }

    public final void cancel(String broadcast) {
        this.cancel();

        Bukkit.broadcastMessage(TextColor.colorize(broadcast));
    }

    protected final void runTaskTimer(long delay, long period) {
        this.runTask(Bukkit.getScheduler().runTaskTimer(plugin, this::run, delay, period));
    }

    public static void runTaskLater(SpigotPlugin plugin, long delay, java.lang.Runnable run) {
        Bukkit.getScheduler().runTaskLater(plugin, run, delay);
    }

    protected void runTask(BukkitTask task) {
        if (isRunning()) throw new IllegalStateException("Already Running as: " + task.getTaskId());

        this.state = task.getTaskId();
    }

    public final boolean isRunning() {
        return state != -1;
    }

    public void onStop() {}

    public final boolean isAutoStart() {
        return getClass().isAnnotationPresent(AutoStart.class);
    }
}
