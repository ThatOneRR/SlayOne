package me.thatonerr.slayone.blocks;

import me.thatonerr.slayone.Game;
import me.thatonerr.slayone.framework.Hitbox;
import me.thatonerr.slayone.framework.Tile;
import me.thatonerr.slayone.values.TileType;

public abstract class Block extends Tile {

    protected Hitbox hitbox;

    public Block(Hitbox hitbox, TileType tileType, Game game, int position, int tileX, int tileY, int width, int height) {
        super(tileType, game, position, tileX, tileY, width, height);
        this.hitbox = hitbox;
    }

    public abstract int getMovability(float incVelX, float incVelY);

    public Hitbox getHitbox() {
        return hitbox;
    }

}
