package de.api.devtools.common.schedules;

import de.api.devtools.common.plugin.MinecraftPlugin;

import javax.annotation.Nonnull;

public class Countdown extends Runnable implements TimeState {

    private int timeLeft;

    public Countdown(@Nonnull MinecraftPlugin plugin, int start, long period) {
        super(plugin, period);
        this.timeLeft = start;
    }

    public Countdown(@Nonnull MinecraftPlugin plugin, int start) {
        this(plugin, start, TimeUtil.ofSeconds(1));
    }

    @Override
    public final void run() {
        if (!isRunning()) return;
        this.setTime(getTime() - 1);
        this.cancelIf(getTime() <= 0);
    }

    @Override
    public final int getTime() {
        return timeLeft;
    }

    @Override
    public final void setTime(int time) {
        this.timeLeft = time;
    }

    @Override
    public void onTimeChange() {}
}
