package de.api.devtools.scoreboard;

import org.bukkit.scheduler.BukkitRunnable;

public abstract class ScoreAnimation extends BukkitRunnable {

    public ScoreAnimation() {
    }

    public abstract void update();

    @Override
    public void run() {
        update();
    }
}
