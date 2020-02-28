package me.thatonerr.slayone.ui;

import me.thatonerr.slayone.Game;
import me.thatonerr.slayone.framework.GameObject;
import me.thatonerr.slayone.managers.ScreenManager;

public abstract class UIScreen extends GameObject {

    protected ScreenManager screenManager;

    public UIScreen(ScreenManager screenManager, Game game, float x, float y) {
        super(game, x, y, game.getWidth(), game.getHeight());
        this.screenManager = screenManager;
    }

    public UIScreen(ScreenManager screenManager, Game game, float xStart, float yStart, int width, int height) {
        super(game, xStart, yStart, width, height);
        this.screenManager = screenManager;
    }

    public abstract void refresh();
    public abstract void onComponentClick(String name);

}
