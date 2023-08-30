public class LifeSimulator {
    private int sizex;
    private int sizey;
    private int[][] grid;
    public LifeSimulator(int sizeX, int sizeY) {
        sizex = sizeX;
        sizey = sizeY;
        grid = new int[sizex][sizey];
    }

    public int getSizeX() {
        return sizex;
    }
    public int getSizeY() {
        return sizey;
    }
    public boolean getCell(int x, int y) {
        if (grid[x][y] == 1) {
            return true;
        }
        return false;
    }

    public void insertPattern(Pattern pattern, int startX, int startY) {
        for(int x = 0; x < pattern.getSizeX(); x++) {
            if ((startX + x) > sizex) {
                break;
            }
            for(int y = 0; y < pattern.getSizeY(); y++) {
                if ((startY + y) > sizey) {
                    break;
                }
                if(pattern.getCell(x, y)) {
                    grid[x+startX][y+startY] = 1;
                }
            }
        }
    }

    public void update() {
        int[][] newgrid;
        newgrid = new int[sizex][sizey];

        // Looping through the grid
        for(int x = 0; x < sizex; x++) {
            for(int y = 0; y < sizey; y++) {

                int alive = 0;
                // loop around cell and count neighbors
                for(int i = -1; i <= 1; i++) {
                    for(int j = -1; j <= 1; j++) {
                        // if it is in the bounds
                        if((x+i >= 0 && x+i < sizex) && (y+j >= 0 && y+j < sizey)) {
                            alive += grid[x+i][y+j];
                        }
                    }
                }
                // Subtracting the current cell
                alive -= grid[x][y];

                // applying game's rules
                // cell has fewer than 2 neighbors
                if(grid[x][y] == 1 && alive < 2) {
                    newgrid[x][y] = 0;
                }
                // cell has 2 or 3 neighbors
                else if(grid[x][y] == 1 && (alive >= 2 && alive <= 3)) {
                    newgrid[x][y] = 1;
                }
                // cell has more than 3 neighbors
                else if(grid[x][y] == 1 && alive < 3) {
                    newgrid[x][y] = 0;
                }
                // dead cell has 3 alive neighbors
                else if(grid[x][y] == 0 && alive == 3) {
                    newgrid[x][y] = 1;
                }
            }
        }
        grid = newgrid;
    }
}
