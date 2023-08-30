public class PatternAcorn extends Pattern {
    private int[][] acorn = new int[][]
    {{0,0,0,0,0,0,0,0,0},{0,0,1,0,0,0,0,0,0},{0,0,0,0,1,0,0,0,0},{0,1,1,0,0,1,1,1,0},{0,0,0,0,0,0,0,0,0}};

    public int getSizeX() {
        return acorn.length;
    }

    public int getSizeY() {
        return acorn[0].length;
    }

    public boolean getCell(int x, int y) {
        if (acorn[x][y] == 1) {
            return true;
        }
        return false;
    }
}
