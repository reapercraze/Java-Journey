public class Queue<E> {
    private Node<E> front;
    private Node<E> rear;

    // Node class to represent elements in the queue
    private static class Node<E> {
        E data;
        Node<E> next;

        public Node(E data) {
            this.data = data;
            this.next = null;
        }
    }

    // Constructor to initialize an empty queue
    public Queue() {
        front = rear = null;
    }

    // Method to enqueue an element
    public void enqueue(E value) {
        Node<E> newNode = new Node<>(value);
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }

    // Method to dequeue an element
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        E data = front.data;
        front = front.next;
        if (front == null) {
        // If the last element is dequeued, set rear to null
            rear = null;
        }
        return data;
    }

    // Method to check if the queue is empty
    public boolean isEmpty() {
        return front == null;
    }
}