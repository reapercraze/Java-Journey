public class PriorityQueue<E extends Comparable<E>> {

    private Node<E> root; // Root of the heap

    private static class Node<E> {
        public E value; // The data in the node
        public Node<E> left; // Left child
        public Node<E> right; // Right child
        public int npl; // null path length

        public Node(E value, Node<E> left, Node<E> right) {
            this.value = value;
            this.left = left;
            this.right = right;
            this.npl = 0;
        }
    }

    public PriorityQueue() {
        this.root = null;
    }

    public void enqueue(E value) {
        Node<E> newNode = new Node<>(value, null, null);
        this.root = merge(this.root, newNode);
    }

    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("PriorityQueue is empty");
        }
        E minValue = this.root.value;
        this.root = merge(this.root.left, this.root.right);
        return minValue;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    private Node<E> merge(Node<E> t1, Node<E> t2) {
        if (t1 == null) return t2;
        if (t2 == null) return t1;

        if (t1.value.compareTo(t2.value) > 0) {
            Node<E> temp = t1;
            t1 = t2;
            t2 = temp;
        }

        t1.right = merge(t1.right, t2);

        if (getNpl(t1.left) < getNpl(t1.right)) {
            Node<E> temp = t1.left;
            t1.left = t1.right;
            t1.right = temp;
        }

        t1.npl = getNpl(t1.right) + 1;
        return t1;
    }

    private int getNpl(Node<E> node) {
        return node == null ? -1 : node.npl;
    }
}