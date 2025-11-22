package de.api.plugins.functionals;

import org.bukkit.scheduler.BukkitTask;

public abstract class Scheduler implements Runnable {

    private int taskId = -1;
    private boolean running = false;

    @Override
    public int getTaskID() {
        if (taskId == -1)
            throw new IllegalStateException("Not scheduled yet");

        return taskId;
    }

    @Override
    public void setState(BukkitTask task) {
        this.taskId = task.getTaskId();
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public void setRunning(boolean running) {
        this.running = running;
    }
}
