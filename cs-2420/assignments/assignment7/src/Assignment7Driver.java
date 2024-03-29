import java.io.File;
import java.util.Scanner;

public class Assignment7Driver {
    public static void main(String[] args) {
        Graph g1 = buildGraph("demands1.txt");
        System.out.println("-- Max Flow: demands1.txt --");
        int flow1 = g1.findMaxFlow(0, 5, true);
        System.out.printf("Total Flow: %d\n", flow1);
        System.out.println("\n-- Min Cut: demands1.txt --");
        g1.findMinCut(0);

        Graph g2 = buildGraph("demands2.txt");
        System.out.println("\n-- Max Flow: demands2.txt --");
        int flow2 = g2.findMaxFlow(0, 8, true);
        System.out.printf("Total Flow: %d\n", flow2);
        System.out.println("\n-- Min Cut: demands2.txt --");
        g2.findMinCut(0);

        Graph g3 = buildGraph("demands3.txt");
        System.out.println("\n-- Max Flow: demands2.txt --");
        int flow3 = g3.findMaxFlow(0, 8, true);
        System.out.printf("Total Flow: %d\n", flow3);
        System.out.println("\n-- Min Cut: demands3.txt --");
        g3.findMinCut(0);

        Graph g4 = buildGraph("demands4.txt");
        System.out.println("\n-- Max Flow: demands4.txt --");
        int flow4 = g4.findMaxFlow(0, 7, true);
        System.out.printf("Total Flow: %d\n", flow4);
        System.out.println("\n-- Min Cut: demands4.txt --");
        g4.findMinCut(0);

        Graph g5 = buildGraph("demands5.txt");
        System.out.println("\n-- Max Flow: demands5.txt --");
        int flow5 = g5.findMaxFlow(0, 8, true);
        System.out.printf("Total Flow: %d\n", flow5);
        System.out.println("\n-- Min Cut: demands5.txt --");
        g5.findMinCut(0);

        Graph g6 = buildGraph("demands6.txt");
        System.out.println("\n-- Max Flow: demands6.txt --");
        int flow6 = g6.findMaxFlow(0, 7, true);
        System.out.printf("Total Flow: %d\n", flow6);
        System.out.println("\n-- Min Cut: demands6.txt --");
        g6.findMinCut(0);
    }

    public static Graph buildGraph(String filename) {
        try {
            Scanner input = new Scanner(new File(filename));
            int vertexCount = input.nextInt();
            Graph g = new Graph(filename, vertexCount);

            while (input.hasNextInt()) {
                int v1 = input.nextInt();
                int v2 = input.nextInt();
                int capacity = input.nextInt();
                if (!g.addEdge(v1, v2, capacity)) {
                    throw new Exception();
                }
            }
            input.close();
            return g;
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Should throw an exception, but, well, deal with it.
        return null;
    }
}
