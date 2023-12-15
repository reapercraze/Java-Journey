import java.util.*;

public class Graph {
    private final GraphNode[] vertices;  // Adjacency list for graph.
    private final String name;  //The file from which the graph was created.
    private int[][] residualGraph;

    public Graph(String name, int vertexCount) {
        this.name = name;

        vertices = new GraphNode[vertexCount];
        for (int vertex = 0; vertex < vertexCount; vertex++) {
            vertices[vertex] = new GraphNode(vertex);
        }
    }

    public boolean addEdge(int source, int destination, int capacity) {
        // A little bit of validation
        if (source < 0 || source >= vertices.length) return false;
        if (destination < 0 || destination >= vertices.length) return false;

        // This adds the actual requested edge, along with its capacity
        vertices[source].addEdge(source, destination, capacity);
        vertices[destination].addEdge(destination, source, 0);

        return true;
    }

    /**
     * Algorithm to find max-flow in a network
     */
    public int findMaxFlow(int s, int t, boolean report) {
        // Initialize total flow
        int totalFlow = 0;

        // Initialize the residual graph
        this.residualGraph = new int[vertices.length][vertices.length];
        for (int w = 0; w < vertices.length; w++) {
            for (GraphNode.EdgeInfo edge : vertices[w].successor) {
                residualGraph[edge.from][edge.to] = edge.capacity;
            }
        }

        // Augment the flow while there is path from source to sink
        while (hasAugmentingPath(s, t)) {
            // Find the maximum flow through the path found.
            int pathFlow = Integer.MAX_VALUE;
            for (int v = t; v != s; v = vertices[v].parent) {
                int w = vertices[v].parent;
                pathFlow = Math.min(pathFlow, residualGraph[w][v]);
            }

            // Store the augmenting path in a list or stack
            LinkedList<Integer> path = new LinkedList<>();
            for (int v = t; v != s; v = vertices[v].parent) {
                path.addFirst(v);
            }
            path.addFirst(s);

            // Update the residual capacities
            for (int v = t; v != s; v = vertices[v].parent) {
                int w = vertices[v].parent;
                residualGraph[w][v] -= pathFlow;
                residualGraph[v][w] += pathFlow;
            }

            // Print the augmenting path in order
            if (report) {
                System.out.print("Flow " + pathFlow + ": ");
                for (int v : path) {
                    System.out.print(v + " ");
                }
                System.out.println();
            }


            totalFlow += pathFlow;
        }

        // Display the flow through each edge
        if (report) {
            System.out.println();
            for (int w = 0; w < vertices.length; w++) {
                for (GraphNode.EdgeInfo edge : vertices[w].successor) {
                    int flowThroughEdge = edge.capacity - residualGraph[edge.from][edge.to];
                    if (flowThroughEdge > 0) {
                        System.out.println("Edge(" + edge.from + ", " + edge.to + ") transports " + flowThroughEdge + " items");
                    }
                }
            }
        }

        return totalFlow;
    }

    /**
     * Algorithm to find an augmenting path in a network
     */
    private boolean hasAugmentingPath(int s, int t) {
        // Mark all vertices as not visited
        for (GraphNode vertex : vertices) {
            vertex.visited = false;
            vertex.parent = -1;
        }

        // Create a queue for BFS and mark the source node as visited
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        vertices[s].visited = true;

        while (!queue.isEmpty()) {
            int w = queue.poll();

            for (GraphNode.EdgeInfo edge : vertices[w].successor) {
                int v = edge.to;

                // If the vertex hasn't been visited and the edge has residual capacity
                if (!vertices[v].visited && residualGraph[w][v] > 0) {
                    // Setting the parent for the path
                    vertices[v].parent = w;
                    vertices[v].visited = true;
                    queue.add(v);

                    // If we have reached the sink, return true
                    if (v == t) {
                        return true;
                    }
                }
            }
        }

        // Return false if we can't reach sink from source
        return false;
    }

    /**
     * Algorithm to find the min-cut edges in a network
     */
    public void findMinCut(int s) {
        // Step 1: Identify reachable vertices
        boolean[] isReachable = new boolean[vertices.length];
        dfs(s, isReachable);

        // Step 2: Find min cut edges
        for (int i = 0; i < vertices.length; i++) {
            for (GraphNode.EdgeInfo edge : vertices[i].successor) {
                if (isReachable[i] && !isReachable[edge.to]) {
                    System.out.println("Min Cut Edge: (" + i + ", " + edge.to + ")");
                }
            }
        }
    }

    /**
     * Depth-First Search Algorithm
     */
    private void dfs(int v, boolean[] visited) {
        visited[v] = true;
        for (GraphNode.EdgeInfo edge : vertices[v].successor) {
            if (!visited[edge.to] && residualGraph[v][edge.to] > 0) {
                dfs(edge.to, visited);
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("The Graph " + name + " \n");
        for (var vertex : vertices) {
            sb.append((vertex.toString()));
        }
        return sb.toString();
    }
}
