package de.api.devtools.run;

//: type of scheduler to count time and execute code on time change.
public class Timer extends Scheduler {

    public enum TimerTask {
        INCREASE_VALUE,
        DECREASE_VALUE;
    }

    protected final TimerTask task;
    protected int time;

    public Timer(TimerTask task, int value, long delay, long period) {
        super(delay, period);
        this.task = task;
        this.time = value;
    }

    public Timer(TimerTask task, int value, long period) {
        this(task, value, 0, period);
    }

    @Override
    protected void run() {
        setTime(switch (task) {
            case INCREASE_VALUE -> time++;
            case DECREASE_VALUE -> time--;
        });

        if (task == TimerTask.DECREASE_VALUE && time <= 0)
            cancel();
    }

    public void onTimeChange() {}

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;

        this.onTimeChange();
    }
}
