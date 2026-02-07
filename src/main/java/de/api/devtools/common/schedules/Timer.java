package de.api.devtools.common.schedules;

import de.api.devtools.common.plugin.MinecraftPlugin;

public class Timer extends Runnable implements TimeState {

    private int time;

    public Timer(MinecraftPlugin plugin, int start, long delay, long period) {
        super(plugin, delay, period);
        this.time = start;
    }

    @Override
    public final void run() {
        if (!isRunning()) return;
        this.setTime(time + 1);
    }

    @Override
    public final int getTime() {
        return time;
    }

    @Override
    public final void setTime(int time) {
        this.time = time;
    }

    @Override
    public void onTimeChange() {}
}
