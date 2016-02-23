package tanknetbeansproject.Game;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import tanknetbeansproject.view.ClientUI;
import tanknetbeansproject.Model.BrickCell;
import tanknetbeansproject.Model.Cell;
import tanknetbeansproject.Model.Map;
import tanknetbeansproject.Model.Player;
import tanknetbeansproject.Model.StoneCell;
import tanknetbeansproject.Model.WaterCell;

public class GameEngine {

    private Map map = new Map();
    private ArrayList<Player> players = new ArrayList<Player>();
    private Player player = null;
    private ClientUI clientUI = null;

    public void createMap(String update) {
        ArrayList<BrickCell> brickCellArray = new ArrayList<BrickCell>();
        ArrayList<StoneCell> stoneCellArray = new ArrayList<StoneCell>();
        ArrayList<WaterCell> waterCellArray = new ArrayList<WaterCell>();

        int x = 0;

        // Matching and splitting coordinates
        String splittedArray[] = update.split(":");
        for (String part : splittedArray) {
            Matcher m = Pattern.compile("(\\d),(\\d)").matcher(part);
            while (m.find()) {
                int xValue = Integer.parseInt(m.group(1));
                int yValue = Integer.parseInt(m.group(2));
                if (x == 2) {
                    BrickCell brickCell = new BrickCell(xValue, yValue);
                    brickCellArray.add(brickCell);
                } else if (x == 3) {
                    StoneCell stoneCell = new StoneCell(xValue, yValue);
                    stoneCellArray.add(stoneCell);
                } else if (x == 4) {
                    WaterCell waterCell = new WaterCell(xValue, yValue);
                    waterCellArray.add(waterCell);
                }
            }
            x++;
        }

        // Filling the Map With Cells
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                map.updateMap(i, j, new Cell());
            }
        }

        // Filling the Special Cells
        for (BrickCell brickCell : brickCellArray) {
            map.updateMap(brickCell.getxValue(), brickCell.getyValue(),
                    brickCell);
        }
        for (StoneCell stoneCell : stoneCellArray) {
            map.updateMap(stoneCell.getxValue(), stoneCell.getyValue(),
                    stoneCell);
        }
        for (WaterCell waterCell : waterCellArray) {
            map.updateMap(waterCell.getxValue(), waterCell.getyValue(),
                    waterCell);
        }

        clientUI.drawMap(map);
        // TODO Auto-generated method stub
    }

    public void updateMap(String update) {
        if (update.equals("OBSTACLE#")) {
            clientUI.showMessage("Obstacle..", 1000);
        } else if (update.equals("CELL_OCCUPIED#")) {
            clientUI.showMessage("Sory.Cell Occupued.", 2000);
        } else if (update.equals("DEAD#")) {
            clientUI.showMessage("Dead.", 1000);
        } else if (update.equals("TOO_QUICK#")) {
            clientUI.showMessage("Too quick, Try again.", 2000);
        } else if (update.equals("INVALID_CELL#")) {
            clientUI.showMessage("Invalid cell", 2000);
        } else if (update.equals("GAME_HAS_FINISHED#")) {
            clientUI.showMessage("GAME FINISHED", 100000);
        } else if (update.equals("GAME_NOT_STARTED_YET#")) {
            clientUI.showMessage("GAME NOT STARTED YET", 2000);
        } else if (update.equals("NOT_A_VALID_CONTESTANT#")) {
            clientUI.showMessage("Not a valid contestant.", 2000);
        } else if (update.charAt(0) == 'I') {
            createMap(update);
        } else if (update.charAt(0) == 'C') {
            manageCoins(update);
        } else if (update.charAt(0) == 'G') {
            updateExistMap(update);
        } else if (update.charAt(0) == 'S') {
            initPlayers(update);
        } else if (update.charAt(0) == 'L') {
            manageLifePack(update);
        }
        printMap();
    }

    private void manageLifePack(String update) {
        // L:<x>,<y>:<LT>#
        String detail[] = update.split(":");
        int xValue = Integer.parseInt(detail[1].substring(0, 1));
        int yValue = Integer.parseInt(detail[1].substring(2, 3));
        String time = detail[2];

        map.getCell(xValue, yValue).addLifePack(
                time.substring(0, time.length() - 1));
        // TODO Auto-generated method stub

    }

    private void initPlayers(String update) {
        update = update.substring(2);
        String detail[] = update.split(";");
        String number = detail[0];
        int xValue = Integer.parseInt(detail[1].substring(0, 1));
        int yValue = Integer.parseInt(detail[1].substring(2, 3));
        int direction = Integer.parseInt(detail[2].substring(0, 1));

        player = new Player(number, xValue, yValue, direction);
        player.placeTank(map, xValue, yValue);

        clientUI.setPlayer(player);

        // TODO Auto-generated method stub
    }

    private void manageCoins(String update) {
        // C:<x>,<y>:<LT>:<Val>#
        String detail[] = update.split(":");
        int xValue = Integer.parseInt(detail[1].substring(0, 1));
        int yValue = Integer.parseInt(detail[1].substring(2, 3));
        String time = detail[2];
        String value = detail[3];

        map.getCell(xValue, yValue).addCoin(time, value.substring(0, value.length() - 1));

        // TODO Auto-generated method stub
    }

    private void updateExistMap(String update) {
        // update =
        // "P0;1,0;1;0;100;0;0:P1;2,9;1;0;100;0;0:P2;8,0;3;0;100;0;0:P3;7,9;3;0;100;0;0:P4;4,5;1;0;100;0;0:8,6,0;6,3,0;7,1,0;1,4,0;0,4,0;4,8,0;1,3,0#";
        // P1;< player location x>,< player location y>;<Direction>;< whether
        // shot>;<health>;< coins>;< points>

        String splittedArray[] = update.split(":");
        for (String part : splittedArray) {
            if ((part.charAt(0) != 'G') && (part.charAt(0) != 'P')) {
                // < x>,<y>,<damage-level>;
                String updateData[] = part.split(";");
                for (String data : updateData) {
                    updateBrickCells(Integer.parseInt(data.substring(0, 1)),
                            Integer.parseInt(data.substring(2, 3)), Integer.parseInt(data.substring(4, 5)));
                }
            }
        }
        for (String part : splittedArray) {
            if (part.charAt(0) == 'P') {
                String updateData[] = part.split(";");
                player.updateLocation(map,
                        Integer.parseInt(updateData[1].substring(0, 1)),
                        Integer.parseInt(updateData[1].substring(2, 3)));
                String direction = updateData[2];
                String shot = updateData[3];
                String health = updateData[4];
                String coin = updateData[5];
                String point = updateData[6];

                player.updateDirectionCoinHelthPoints(clientUI, direction, coin, health, point);

                if (Integer.parseInt(shot) == 1) {
                    clientUI.fireBullet();
                }
                
            }
        }

    }

    private void updateBrickCells(int xValue, int yValue, int damageLevel) {
        if (damageLevel == 4) {
            System.err.println("Name :" + map.getFullMap()[xValue][yValue].getClass().getName());
            if (map.getFullMap()[xValue][yValue].getClass().getName().equals("tanknetbeansproject.Model.BrickCell")) {
                System.err.println("Came in");
                map.getFullMap()[xValue][yValue] = new Cell();
            }
        } else {
            ((BrickCell) map.getFullMap()[xValue][yValue]).setDamageLevel(damageLevel);
        }
        // TODO Auto-generated method stub
    }

    public void printMap() {
//        for (int i = 0; i < 10; i++) {
//            for (int j = 0; j < 10; j++) {
//                System.out.print(map.getFullMap()[j][i] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println("========================================================\n\n");

        clientUI.updateInterface(map);
    }

    public void setClientUI(ClientUI clientUI) {
        this.clientUI = clientUI;
        // TODO Auto-generated method stub
    }

}
