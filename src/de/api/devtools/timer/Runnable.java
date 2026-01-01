package de.api.devtools.timer;

import de.api.devtools.utils.Executable;
import de.api.devtools.plugin.SpigotPlugin;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

public interface Runnable extends Executable {

    default void cancel() {
        Bukkit.getScheduler().cancelTask(getTaskID());
    }

    int getTaskID();

    default void runTaskTimer(long delay, long period) {
        checkState();
        setState(Bukkit.getScheduler().runTaskTimer(SpigotPlugin.getInstance(), this::execute, delay, period));
    }

    default void runTaskLater(long delay) {
        checkState();
        setState(Bukkit.getScheduler().runTaskLater(SpigotPlugin.getInstance(), this::execute, delay));
    }

    default void checkState() {
        if (getTaskID() == -1) throw new IllegalStateException("Already scheduled as " + getTaskID());
    }

    void setState(BukkitTask task);

    boolean isRunning();

    void setRunning(boolean running);

    default void start() {
        if (!isRunning()) setRunning(true);
    }

    default void stop() {
        if (isRunning()) setRunning(false);
    }
}
