class TaskByDeadline extends Task {
    public TaskByDeadline(int ID, int start, int deadline, int duration) {
        super(ID, start, deadline, duration);
    }

    @Override
    public int compareTo(Task o) {
        int deadlineComparison = Integer.compare(this.deadline, o.deadline);
        if (deadlineComparison == 0) {
            return Integer.compare(this.duration, o.duration);
        }
        return deadlineComparison;
    }
}