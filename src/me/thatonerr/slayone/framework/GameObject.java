package me.thatonerr.slayone.framework;

import me.thatonerr.slayone.Game;

import java.awt.*;

public abstract class GameObject {

    protected Game game;
    protected float xStart, yStart, xEnd, yEnd;
    protected int width, height;

    public GameObject(Game game, float xStart, float yStart, int width, int height) {
        this.game = game;
        this.xStart = xStart;
        this.yStart = yStart;
        this.width = width;
        this.height = height;
        updatePosition();
    }

    public void updatePosition() {
        xEnd = xStart + width - 1;
        yEnd = yStart + height - 1;
    }

    public abstract void tick();
    public abstract void render(Graphics2D graphics);

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public float getXStart() {
        return xStart;
    }

    public void setXStart(float xStart) {
        this.xStart = xStart;
    }

    public float getYStart() {
        return yStart;
    }

    public void setYStart(float yStart) {
        this.yStart = yStart;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getXEnd() {
        return xEnd;
    }

    public void setXEnd(float xEnd) {
        this.xEnd = xEnd;
    }

    public float getYEnd() {
        return yEnd;
    }

    public void setYEnd(float yEnd) {
        this.yEnd = yEnd;
    }

}
