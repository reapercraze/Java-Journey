public class DisjointSet {
    private int[] parent; // Parent of each element
    private int[] rank;   // Size of the tree for each root node

    public DisjointSet(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int node) {
        if (node == parent[node]) {
            return node;
        }
        parent[node] = find(parent[node]); // Path compression
        return parent[node];
    }

    public void union(int node1, int node2) {
        int root1 = find(node1);
        int root2 = find(node2);

        if (root1 != root2) {
            // Union by rank (size)
            if (rank[root1] < rank[root2]) {
                parent[root1] = root2;
                rank[root2] += rank[root1];
            } else {
                parent[root2] = root1;
                rank[root1] += rank[root2];
            }
        }
    }
}