public class PatternGlider extends Pattern {
    private int[][] glider = new int[][]
            {{0,0,0,0,0},
            {0,0,1,0,0},
            {0,0,0,1,0},
            {0,1,1,1,0},
            {0,0,0,0,0}};

    public int getSizeX() {
        return glider.length;
    }

    public int getSizeY() {
        return glider[0].length;
    }

    public boolean getCell(int x, int y) {
        if (glider[x][y] == 1) {
            return true;
        }
        return false;
    }
}
