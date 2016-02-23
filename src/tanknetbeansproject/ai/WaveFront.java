/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tanknetbeansproject.ai;

import tanknetbeansproject.Model.Cell;

/**
 *
 * @author Neo_
 */
public class WaveFront {

//grid vars
    int X_GRID_SIZE = 10;
    int Y_GRID_SIZE = 10;
    int node = 50;
    int NaN = 100;
//do not modify
    int InitX = 0, InitY = 0;
    int TargetX = 0, TargetY = 0;
    char curDir = 'S'; // assign this for dryrun 2 only
    int boxPos = 0, boyPos = 0; //assign this after dry run
//original map

    
    public String[][] pathMap = new String[10][10];
    public int[][] pathFindMap = new int[10][10];

    public void reviewMap(Cell[][] map) {
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                if (map[x][y].toString().equals("S") || map[x][y].toString().equals("W")) {
                    pathgrid[y][x] = 50;
                } else {
                    pathgrid[y][x] = 100;
                }
            }
        }
    }
    //int[][] pathgrid = {{NaN, node, node, node, NaN, NaN, NaN, node, node, NaN}, {NaN, NaN, node, node, node, NaN, NaN, node, node, NaN}, {node, NaN, node, node, NaN, NaN, NaN, NaN, node, NaN}, {NaN, NaN, node, NaN, NaN, node, NaN, node, node, NaN}, {node, NaN, NaN, NaN, node, NaN, NaN, node, node, NaN}, {node, node, node, node, node, node, NaN, NaN, NaN, NaN}, {NaN, NaN, NaN, NaN, NaN, NaN, node, NaN, node, NaN},{NaN, NaN, NaN, NaN, NaN, NaN, node, NaN, node, NaN},{NaN, NaN, NaN, NaN, NaN, NaN, node, NaN, node, NaN},{NaN, NaN, NaN, NaN, NaN, NaN, node, NaN, node, NaN}};
    public int[][] pathgrid = new int[X_GRID_SIZE][Y_GRID_SIZE];
    //{{NaN, node, node, node, NaN, NaN, NaN, node, node, NaN}, {NaN, NaN, node, node, node, NaN, NaN, node, node, NaN}, {node, NaN, node, node, NaN, NaN, NaN, NaN, node, NaN}, {NaN, NaN, node, NaN, NaN, node, NaN, node, node, NaN}, {node, NaN, NaN, NaN, node, NaN, NaN, node, node, NaN}, {node, node, node, node, node, node, NaN, NaN, NaN, NaN}, {NaN, NaN, NaN, NaN, NaN, NaN, node, NaN, node, NaN}};
    public int pathgrid1[][] = new int[X_GRID_SIZE][Y_GRID_SIZE];
    char path[] = new char[1000];
    public char headDir[] = new char[1000];
    public char newheadDir[] = new char[1000];
    public char finalPath[] = new char[1000];
    int pathCount = 0;
    int i = 0, j = 0;
    int row = 0, column = 0;

    public char[] waveFront(int targetX, int targetY, int initx, int inity, char dir) {
        int steps = 0;
        curDir = dir;
        int count = 0;

        InitX = initx;
        InitY = inity;
        TargetX = targetX;
        TargetY = targetY;

        for (i = 0; i < X_GRID_SIZE; i++) {
            for (j = 0; j < Y_GRID_SIZE; j++) {
                pathgrid1[i][j] = NaN;
            }
        }

        for (i = 0; i < X_GRID_SIZE; i++) {
            for (j = 0; j < Y_GRID_SIZE; j++) {
                pathgrid1[i][j] = pathgrid[i][j];
            }
        }
        waveFill(InitX, InitY);
        traceBack();

        for (i = 0; i < 100; i++) {
            if (path[i] != '\0') {
                finalPath[steps++] = path[i];

            }

        }
        for (i = 0; i < 100; i++) {
            if (finalPath[i] != '\0') {
                count++;
                //System.out.print(finalPath[i]);
            }
        }
        int tempIterator = 0;
        for (i = 99; i >= 0; i--) {
            if (headDir[i] != '\0') {
                newheadDir[tempIterator++] = headDir[i];
            }
        }

        for (i = 0; i < tempIterator; i++) {
            System.err.println(newheadDir[i]);
        }
        return newheadDir;
    }

    int selectMin(int x, int y) {
        int temp[] = {200, 200, 200, 200};
        int a, i, j;

        if ((x + 1) < X_GRID_SIZE && pathgrid1[x + 1][y] != NaN && pathgrid1[x + 1][y] != node) {
            temp[0] = pathgrid1[x + 1][y];
        }
        if ((y + 1) < Y_GRID_SIZE && pathgrid1[x][y + 1] != NaN && pathgrid1[x][y + 1] != node) {
            temp[1] = pathgrid1[x][y + 1];
        }
        if ((x - 1) >= 0 && pathgrid1[x - 1][y] != NaN && pathgrid1[x - 1][y] != node) {
            temp[2] = pathgrid1[x - 1][y];
        }
        if ((y - 1) >= 0 && pathgrid1[x][y - 1] != NaN && pathgrid1[x][y - 1] != node) {
            temp[3] = pathgrid1[x][y - 1];
        }
        for (i = 0; i < 4; ++i) {
            for (j = i + 1; j < 4; ++j) {
                if (temp[i] > temp[j]) {
                    a = temp[i];
                    temp[i] = temp[j];
                    temp[j] = a;
                }
            }
        }

        if (temp[0] < 100) {
            return temp[0];
        } else {
            return NaN - 1;
        }
    }

    void waveFill(int x, int y) {

        pathgrid1[x][y] = 0;
        int notFilled = 1, count1 = 0;

        while (count1 < 100) {
            notFilled = 0;
            for (i = 0; i < X_GRID_SIZE; i++) {
                for (j = 0; j < Y_GRID_SIZE; j++) {
                    if (pathgrid1[i][j] == NaN) {
                        pathgrid1[i][j] = selectMin(i, j) + 1;
                    }
                }
            }
            count1++;
            notFilled = 0;
        }

        for (i = 0; i < X_GRID_SIZE; i++) {
            for (j = 0; j < Y_GRID_SIZE; j++) {
                System.out.print(pathgrid1[i][j] + "  ");

            }
            System.out.println("");
        }
    }

    char pairwiseDir(char dir1, char dir2) {
        if (dir1 == 'N' && dir2 == 'N') {
            return 'F';
        }
        if (dir1 == 'N' && dir2 == 'W') {
            return 'L';
        }
        if (dir1 == 'N' && dir2 == 'E') {
            return 'R';
        }
        if (dir1 == 'N' && dir2 == 'S') {
            return 'B';
        }
        if (dir1 == 'E' && dir2 == 'N') {
            return 'L';
        }
        if (dir1 == 'E' && dir2 == 'S') {
            return 'R';
        }
        if (dir1 == 'E' && dir2 == 'E') {
            return 'F';
        }
        if (dir1 == 'E' && dir2 == 'W') {
            return 'B';
        }

        if (dir1 == 'W' && dir2 == 'S') {
            return 'L';
        }
        if (dir1 == 'W' && dir2 == 'N') {
            return 'R';
        }
        if (dir1 == 'W' && dir2 == 'W') {
            return 'F';
        }
        if (dir1 == 'W' && dir2 == 'E') {
            return 'B';
        }

        if (dir1 == 'S' && dir2 == 'S') {
            return 'F';
        }
        if (dir1 == 'S' && dir2 == 'E') {
            return 'L';
        }
        if (dir1 == 'S' && dir2 == 'W') {
            return 'R';
        }
        if (dir1 == 'S' && dir2 == 'N') {
            return 'B';
        }
        return '0';
    }

    void traceBack() {
        int count = 0, t = 0;
        int pathCount = pathgrid1[TargetX][TargetY];
        int i = TargetX, j = TargetY;
        System.out.println(pathCount);
        for (t = pathCount; t >= 0; t--) {

            if ((i + 1) < X_GRID_SIZE && pathgrid1[i + 1][j] == t) {
                headDir[count++] = 'N';
                i++;
                continue;
            }
            if ((j + 1) < Y_GRID_SIZE && pathgrid1[i][j + 1] == t) {
                headDir[count++] = 'E';
                j++;
                continue;
            }
            if ((i - 1) >= 0 && pathgrid1[i - 1][j] == t) {
                headDir[count++] = 'S';
                i--;
                continue;

            }
            if ((j - 1) >= 0 && pathgrid1[i][j - 1] == t) {
                headDir[count++] = 'W';
                j--;
                continue;
            }

        }

        for (t = 0; t < 100; t++) {
            if (headDir[t] == 'S') {
                headDir[t] = 'N';
                continue;
            }
            if (headDir[t] == 'N') {
                headDir[t] = 'S';
                continue;
            }
            if (headDir[t] == 'E') {
                headDir[t] = 'W';
                continue;
            }
            if (headDir[t] == 'W') {
                headDir[t] = 'E';
                continue;
            }
        }
        count = 0;

        for (t = 99; t >= 0; t--) {

            {
                if (headDir[t] != '\0') {
                    path[count] = pairwiseDir(curDir, headDir[t]);
                    curDir = headDir[t];
                    count++;
                }

            }
        }
    }

    public static void main(String[] args) {
        WaveFront w = new WaveFront();
        //Player newPlayer=new Player();
//        w.reviewMap();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(w.pathFindMap[i][j] + " ");
            }
            System.out.println("");
        }
        for (int i = 0; i < 10; i++) {
            System.arraycopy(w.pathFindMap[i], 0, w.pathgrid[i], 0, 10);
        }

        w.waveFront(5, 5, 0, 0, 'N');

        //new WaveFront().waveFront(5,5,newPlayer.getPosX(),newPlayer.getPosY(),'N');
    }
}

