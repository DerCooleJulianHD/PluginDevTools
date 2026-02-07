package de.api.devtools.common.scoreboard;

import org.bukkit.scheduler.BukkitRunnable;

public abstract class ScoreAnimation extends BukkitRunnable {

    public ScoreAnimation() {
    }

    public abstract void update();

    @Override
    public final void run() {
        update();
    }
}
