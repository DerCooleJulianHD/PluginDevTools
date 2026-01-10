package de.api.devtools.schedules;

import de.api.devtools.plugin.SpigotPlugin;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

public interface Runnable {

    long getDelay();

    long getPeriod();

    default boolean isDelayed() {
        return getDelay() > 0;
    }

    default boolean isRunning() {
        return getTaskId() != -1;
    }

    default void cancel() {
        if (!isRunning())
            return;

        Bukkit.getScheduler().cancelTask(getTaskId());

        setTaskId(-1);
        onStop();
    }

    default void cancelIf(boolean expression) {
        if (expression) cancel();
    }

    void onStop();

    void onStart();

    default void start() {
        if (isRunning())
            throw new IllegalStateException("Already running as " + getTaskId());

        final BukkitTask task = Bukkit.getScheduler().runTaskTimer(
                SpigotPlugin.getInstance(),
                this::run,
                getDelay(),
                getPeriod()
        );

        setTaskId(task.getTaskId());
        onStart();
    }

    void run();

    default boolean getAutoStart() {
        return getClass().isAnnotationPresent(AutoStart.class);
    }

    int getTaskId();

    void setTaskId(int id);
}
