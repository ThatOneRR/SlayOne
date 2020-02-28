package me.thatonerr.slayone.grounds;

import me.thatonerr.slayone.Game;
import me.thatonerr.slayone.framework.Tile;
import me.thatonerr.slayone.values.TileType;

public abstract class Ground extends Tile {

    public Ground(TileType tileType, Game game, int direction, int tileX, int tileY) {
        super(tileType, game, direction, tileX, tileY, 60, 60);
    }

}
