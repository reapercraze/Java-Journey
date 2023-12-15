class TaskByStart extends Task {
    public TaskByStart(int ID, int start, int deadline, int duration) {
        super(ID, start, deadline, duration);
    }

    @Override
    public int compareTo(Task o) {
        if (this.start != o.start) {
            return Integer.compare(this.start, o.start);
        }
        return Integer.compare(this.deadline, o.deadline);
    }
}