package tanknetbeansproject.Model;

public class Map {

    private Cell fullMap[][] = new Cell[10][10];

    public void updateMap(int x, int y, Cell cell) {
        getFullMap()[x][y] = cell;
    }

    public Cell getCell(int x, int y) {
        return getFullMap()[x][y];
    }

    /**
     * @return the fullMap
     */
    public Cell[][] getFullMap() {
        return fullMap;
    }

    /**
     * @param fullMap the fullMap to set
     */
    public void setFullMap(Cell[][] fullMap) {
        this.fullMap = fullMap;
    }
}
