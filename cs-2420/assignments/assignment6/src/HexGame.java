import java.util.ArrayList;
import java.util.Arrays;

public class HexGame {
    private final int size;
    private final DisjointSet disjointSet;
    private final Color[] grid;

    private final int TOP_EDGE;
    private final int BOTTOM_EDGE;
    private final int LEFT_EDGE;
    private final int RIGHT_EDGE;

    public enum Color {
        NONE, RED, BLUE
    }

    public HexGame(int size) {
        this.size = size;
        int totalCells = size * size;
        this.grid = new Color[totalCells];
        Arrays.fill(grid, Color.NONE);

        // Initialize the disjoint set with extra elements for the edges
        this.disjointSet = new DisjointSet(totalCells + 5);

        // Assigning the last four indexes as edges
        TOP_EDGE = totalCells + 1;
        BOTTOM_EDGE = totalCells + 2;
        LEFT_EDGE = totalCells + 3;
        RIGHT_EDGE = totalCells + 4;
    }

    public boolean playBlue(int position, boolean displayNeighbors) {
        return play(position, Color.BLUE, displayNeighbors);
    }

    public boolean playRed(int position, boolean displayNeighbors) {
        return play(position, Color.RED, displayNeighbors);
    }

    private boolean play(int position, Color playerColor, boolean displayNeighbors) {
        // Get neighbors based on player color
        ArrayList<Integer> neighbors;
        if (playerColor == Color.RED) {
            neighbors = getNeighborsRed(position);
        } else {
            neighbors = getNeighborsBlue(position);
        }

        // Display neighbors if requested
        if (displayNeighbors) {
            displayNeighbors(position, neighbors);
        }

        // Check if the move is valid
        if (position < 1 || position > size * size || isOccupied(position)) {
            // Invalid move
            return false;
        }

        // Update the grid
        grid[position - 1] = playerColor;

        // Perform union operations with neighboring cells of the same color
        for (int neighbor : neighbors) {
            if (neighbor > size * size) { // Check for edge cases
                disjointSet.union(position - 1, neighbor);
            } else if (grid[neighbor - 1] == playerColor) {
                disjointSet.union(position - 1, neighbor - 1);
            }
        }

        // Check for winning condition
        if (playerColor == Color.BLUE) {
            return disjointSet.find(LEFT_EDGE) == disjointSet.find(RIGHT_EDGE);
        } else {
            return disjointSet.find(TOP_EDGE) == disjointSet.find(BOTTOM_EDGE);
        }
    }

    private void displayNeighbors(int position, ArrayList<Integer> neighbors) {
        System.out.print("Cell " + position + ": [ ");

        for (int i = 0; i < neighbors.size(); i++) {
            System.out.print(neighbors.get(i));
            if (i < neighbors.size() - 1) {
                System.out.print(" ");
            }
        }
        System.out.println(" ]");
    }

    private ArrayList<Integer> getNeighborsRed(int position) {
        ArrayList<Integer> neighbors = getNeighbors(position);

        // Add edge neighbors for the top and bottom row for red player
        int row = (position - 1) / size;
        if (row == 0) {
            neighbors.add(TOP_EDGE); // Top row
        }
        if (row == size - 1) {
            neighbors.add(BOTTOM_EDGE); // Bottom row
        }

        return neighbors;
    }

    private ArrayList<Integer> getNeighborsBlue(int position) {
        ArrayList<Integer> neighbors = getNeighbors(position);

        // Add edge neighbors for the left and right column for blue player
        int col = (position - 1) % size;
        if (col == 0) {
            neighbors.add(LEFT_EDGE); // Left column
        }
        if (col == size - 1) {
            neighbors.add(RIGHT_EDGE); // Right column
        }

        return neighbors;
    }

    private ArrayList<Integer> getNeighbors(int position) {
        ArrayList<Integer> neighbors = new ArrayList<>();

        // Convert position to 0-based index
        int index = position - 1;
        int row = index / size;
        int col = index % size;

        // Add left and right neighbors
        if (col > 0) {
            neighbors.add(position - 1); // Left
        }
        if (col < size - 1) {
            neighbors.add(position + 1); // Right
        }

        // Add top and bottom neighbors
        if (row > 0) {
            neighbors.add(position - size); // Top left
            if (col < size - 1) {
                neighbors.add(position - size + 1); // Top right
            }
        }
        if (row < size - 1) {
            if (col > 0) {
                neighbors.add(position + size - 1); // Bottom left
            }
            neighbors.add(position + size); // Bottom right
        }

        return neighbors;
    }

    private boolean isOccupied(int position) {
        int index = position - 1; // Convert to 0-based index
        return grid[index] != Color.NONE;
    }

    public Color[] getGrid() {
        return grid.clone(); // Return a copy to prevent external modification
    }

    public int getSize() {
        return size;
    }
}