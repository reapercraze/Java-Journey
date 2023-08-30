public class PatternBlock extends Pattern {
    private int[][] block = new int[][]
            {{0,0,0,0},
            {0,1,1,0},
            {0,1,1,0},
            {0,0,0,0}};

    public int getSizeX() {
        return block.length;
    }

    public int getSizeY() {
        return block[0].length;
    }

    public boolean getCell(int x, int y) {
        if (block[x][y] == 1) {
            return true;
        }
        return false;
    }
}
