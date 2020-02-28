package me.thatonerr.slayone.utilities;

import me.thatonerr.slayone.Game;
import me.thatonerr.slayone.blocks.Block;
import me.thatonerr.slayone.blocks.Boundary;
import me.thatonerr.slayone.grounds.Alloy1;
import me.thatonerr.slayone.grounds.Ground;

import java.util.HashMap;
import java.util.Map;

import static me.thatonerr.slayone.utilities.Utils.getTextFileResource;

public class MapLoader {

    public static Map<Integer, Map<Integer, Ground>> loadGroundMap(Game game, String folderName) {
        Map<Integer, Map<Integer, Ground>> tileMap = new HashMap<>();
        String[] tileRows = getTextFileResource("/levels/" + folderName + "/grounds.txt").split("\n");
        for (int tileY = 0; tileY < tileRows.length; tileY++) {
            Map<Integer, Ground> rowMap = new HashMap<>();
            String[] tileRow = tileRows[tileY].split(" ");
            for (int tileX = 0; tileX < tileRow.length; tileX++) {
                String[] tileInfo = tileRow[tileX].split(":");
                int tileID, tilePosition;
                try {
                    tileID = Integer.parseInt(tileInfo[0]);
                    tilePosition = 0;
                    if (tileInfo.length > 1) {
                        tilePosition = Integer.parseInt(tileInfo[1]);
                    }
                } catch (NumberFormatException exception) {
                    exception.printStackTrace();
                    System.exit(2);
                    return null;
                }
                rowMap.put(tileX, getGroundByInfo(game, tileID, tilePosition, tileX, tileY));
            }
            tileMap.put(tileY, rowMap);
        }
        return tileMap;
    }

    public static Map<Integer, Map<Integer, Block>> loadBlockMap(Game game, String folderName) {
        Map<Integer, Map<Integer, Block>> tileMap = new HashMap<>();
        String[] tileRows = getTextFileResource("/levels/" + folderName + "/blocks.txt").split("\n");
        for (int tileY = 0; tileY < tileRows.length; tileY++) {
            Map<Integer, Block> rowMap = new HashMap<>();
            String[] tileRow = tileRows[tileY].split(" ");
            for (int tileX = 0; tileX < tileRow.length; tileX++) {
                String[] tileInfo = tileRow[tileX].split(":");
                int tileID, tilePosition;
                try {
                    tileID = Integer.parseInt(tileInfo[0]);
                    tilePosition = 0;
                    if (tileInfo.length > 1) {
                        tilePosition = Integer.parseInt(tileInfo[1]);
                    }
                } catch (NumberFormatException exception) {
                    exception.printStackTrace();
                    System.exit(2);
                    return null;
                }
                rowMap.put(tileX, getBlockByInfo(game, tileID, tilePosition, tileX, tileY));
            }
            tileMap.put(tileY, rowMap);
        }
        return tileMap;
    }

    public static Ground getGroundByInfo(Game game, int tileID, int tilePosition, int tileX, int tileY) {
        switch (tileID) {
            case 1:
                return new Alloy1(game, tilePosition, tileX, tileY);
            default:
                return null;
        }
    }

    public static Block getBlockByInfo(Game game, int tileID, int tilePosition, int tileX, int tileY) {
        switch (tileID) {
            case 2:
                return new Boundary(game, tilePosition, tileX, tileY);
            default:
                return null;
        }
    }

}
