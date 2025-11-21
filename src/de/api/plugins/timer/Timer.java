package de.api.plugins.timer;

import de.api.plugins.plugin.SpigotPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class Timer extends BukkitRunnable implements Executable {

    protected final SpigotPlugin plugin = SpigotPlugin.getInstance();

    protected boolean running = false;
    protected final long delay, period;

    public Timer(long delay, long period) {
        this.delay = delay;
        this.period = period;

        this.runTaskTimer(plugin, delay, period);
    }

    @Override
    public final void run() {
        if (isRunning()) {
            try {
                execute();
            } catch (Exception e) {
                this.cancel();
            }
        }
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public final void start() {
       if (!isRunning()) this.setRunning(true);
    }

    public final void stop() {
        if (isRunning()) this.setRunning(false);
    }

    public final long getDelay() {
        return delay;
    }

    public final long getPeriod() {
        return period;
    }
}
