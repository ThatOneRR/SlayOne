package me.thatonerr.slayone.framework;

import me.thatonerr.slayone.Game;
import me.thatonerr.slayone.values.TileType;

import java.awt.*;

public abstract class Tile extends GameObject {

    protected int tileX, tileY, position;
    protected TileType tileType;

    public Tile(TileType tileType, Game game, int position, int tileX, int tileY, int width, int height) {
        super(game, tileX * width, tileY * height, width, height);
        this.tileType = tileType;
        this.position = position;
        this.tileX = tileX;
        this.tileY = tileY;
    }

    public abstract void renderForPlayer(Graphics2D graphics, FOV fov);

    @Override
    public void render(Graphics2D graphics) {

    }

    public TileType getTileType() {
        return tileType;
    }

    public int getPosition() {
        return position;
    }

    public int getTileX() {
        return tileX;
    }

    public int getTileY() {
        return tileY;
    }

}
