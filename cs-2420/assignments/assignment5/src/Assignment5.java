import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Assignment5 {
    public static void main(String[] args) {
        simpleQueueTest();
        scheduleTasks("taskset1.txt");
        scheduleTasks("taskset2.txt");
        scheduleTasks("taskset3.txt");
        scheduleTasks("taskset4.txt");
        scheduleTasks("taskset5.txt");
    }

    public static void scheduleTasks(String taskFile) {
        Queue<Task> tasksByDeadline = new LinkedList<>();
        Queue<Task> tasksByStart = new LinkedList<>();
        Queue<Task> tasksByDuration = new LinkedList<>();

        readTasks(taskFile, tasksByDeadline, tasksByStart, tasksByDuration);

        schedule("Deadline Priority : " + taskFile, tasksByDeadline);
        schedule("Startime Priority : " + taskFile, tasksByStart);
        schedule("Duration priority : " + taskFile, tasksByDuration);
    }

    public static void simpleQueueTest() {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.enqueue(9);
        queue.enqueue(7);
        queue.enqueue(5);
        queue.enqueue(3);
        queue.enqueue(1);
        queue.enqueue(10);

        while (!queue.isEmpty()) {
            System.out.println(queue.dequeue());
        }
    }

    /**
     * Reads the task data from a file, and creates the three different sets of tasks for each
     */
    public static void readTasks(String filename,
                                 Queue<Task> tasksByDeadline,
                                 Queue<Task> tasksByStart,
                                 Queue<Task> tasksByDuration) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            int id = 1;
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split("\t");
                int start = Integer.parseInt(parts[0]);
                int deadline = Integer.parseInt(parts[1]);
                int duration = Integer.parseInt(parts[2]);

                tasksByDeadline.add(new TaskByDeadline(id, start, deadline, duration));
                tasksByStart.add(new TaskByStart(id, start, deadline, duration));
                tasksByDuration.add(new TaskByDuration(id, start, deadline, duration));
                id++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Given a set of tasks, schedules them and reports the scheduling results
     */
    public static void schedule(String label, Queue<Task> tasks) {
        int currentTime = 1;
        int tasksLate = 0;
        int totalLateTime = 0;
        PriorityQueue<Task> queue = new PriorityQueue<>();

        System.out.println(label);
        // Loop until both the input task queue and the priority queue are empty
        while (!queue.isEmpty() || !tasks.isEmpty()) {
            // Load tasks that can start at the current time
            while (!tasks.isEmpty() && tasks.peek().start <= currentTime) {
                queue.enqueue(tasks.poll());
            }

            // If the queue is not empty, process the next task
            if (!queue.isEmpty()) {
                Task currentTask = queue.dequeue();

                System.out.print(String.format("\tTime %d: ", currentTime) + currentTask);

                currentTask.duration--; // Execute the task for one time unit

                // If task duration is completed
                if (currentTask.duration == 0) {
                    System.out.print(" **");

                    // Check if the task is late
                    if (currentTime >= currentTask.deadline) {
                        int lateTime = currentTime - currentTask.deadline;
                        if (lateTime > 0) {
                            System.out.print(" Late " + lateTime);
                            tasksLate++;
                            totalLateTime += lateTime;
                        }
                    }
                    System.out.println();
                } else {
                    // If the task is not done, put it back in the queue
                    queue.enqueue(currentTask);
                    System.out.println();
                }
            } else {
                // If the queue is empty and no tasks can start, print empty time slot
                System.out.println(String.format("\tTime %3d: ---", currentTime));
            }

            // Advance to the next time unit
            currentTime++;
        }

        System.out.println("Tasks late " + tasksLate + " Total Late " + totalLateTime + "\n");
    }
}
