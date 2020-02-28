package me.thatonerr.slayone.managers;

import me.thatonerr.slayone.Game;
import me.thatonerr.slayone.ui.UIScreen;
import me.thatonerr.slayone.screens.StartLoadingScreen;
import me.thatonerr.slayone.values.GameState;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ScreenManager {

    private Game game;
    private GameState gameState;
    private Map<GameState, UIScreen> screens = new HashMap<>();

    public ScreenManager(Game game) {
        this.game = game;
        gameState = GameState.START_LOADING_SCREEN;
        screens.put(GameState.START_LOADING_SCREEN, new StartLoadingScreen(this, game, 0, 0));
    }

    public void tick() {
        UIScreen screen = screens.get(gameState);
        if (screen != null) screen.tick();
    }

    public void render(Graphics2D graphics) {
        UIScreen screen = screens.get(gameState);
        if (screen != null) screen.render(graphics);
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
        UIScreen screen = screens.get(gameState);
        if (screen != null) screen.refresh();
    }

    public Map<GameState, UIScreen> getScreens() {
        return screens;
    }

}
