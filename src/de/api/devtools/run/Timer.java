package de.api.devtools.run;

//: type of scheduler to count time and execute code on time change
public class Timer extends Scheduler {

    public enum Action {
        INCREASE_VALUE,
        DECREASE_VALUE;
    }

    protected final Action task;
    protected int time;

    protected int end;

    public Timer(Action task, int value, int end, long delay, long period) {
        super(delay, period);
        this.task = task;
        this.time = value;
        this.end = end;
    }

    public Timer(Action task, int value, int end, long period) {
        this(task, value, end, 0, period);
    }

    public Timer(Action task, int value, long period) {
        this(task, value, task == Action.DECREASE_VALUE ? (-Integer.MAX_VALUE) : Integer.MAX_VALUE, period);
    }

    @Override
    protected void run() {
        setTime(switch (task) {
            case INCREASE_VALUE -> time++;
            case DECREASE_VALUE -> time--;
        });

        if (time == end)
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
