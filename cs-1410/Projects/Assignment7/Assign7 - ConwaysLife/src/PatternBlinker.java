public class PatternBlinker extends Pattern {
    private int[][] blincker = new int[][]
            {{0,0,0,0,0},
            {0,0,1,0,0},
            {0,0,1,0,0},
            {0,0,1,0,0},
            {0,0,0,0,0}};

    public int getSizeX() {
        return blincker.length;
    }

    public int getSizeY() {
        return blincker[0].length;
    }

    public boolean getCell(int x, int y) {
        if (blincker[x][y] == 1) {
            return true;
        }
        return false;
    }
}
