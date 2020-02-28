package me.thatonerr.slayone.screens;

import me.thatonerr.slayone.Game;
import me.thatonerr.slayone.assets.GameFont;
import me.thatonerr.slayone.assets.Sprite;
import me.thatonerr.slayone.ui.UIButton;
import me.thatonerr.slayone.ui.UIResponsive;
import me.thatonerr.slayone.ui.UIScreen;
import me.thatonerr.slayone.managers.ScreenManager;
import me.thatonerr.slayone.values.GameState;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class MainMenuScreen extends UIScreen {

    private double delta = 0;
    private long previous;
    private int state = 0, alpha = 255;
    private Color faderColor = new Color(0, 0, 0, alpha);

    private BufferedImage logo = Sprite.LOGO;

    private Map<String, UIResponsive> clickables = new HashMap<>();

    public MainMenuScreen(ScreenManager screenManager, Game game, float x, float y) {
        super(screenManager, game, x, y);
        clickables.put("Singleplayer", new UIButton("Singleplayer", GameFont.MAIN_OPTION_TEXT, Color.LIGHT_GRAY, Color.WHITE, null, null, this, game, width / 4f - 100, height / 2f - 75, 200, 40));
        clickables.put("Multiplayer", new UIButton("Multiplayer", GameFont.MAIN_OPTION_TEXT, Color.LIGHT_GRAY, Color.WHITE, null, null, this, game, width / 4f - 100, height / 2f - 15, 200, 40));
        clickables.put("Character", new UIButton("Character", GameFont.MAIN_OPTION_TEXT, Color.LIGHT_GRAY, Color.WHITE, null, null, this, game, width / 4f - 100, height / 2f + 45, 200, 40));
        clickables.put("Settings", new UIButton("Settings", GameFont.MAIN_OPTION_TEXT, Color.LIGHT_GRAY, Color.WHITE, null, null, this, game, width / 4f - 100, height / 2f + 105, 200, 40));
        clickables.put("Quit", new UIButton("Quit", GameFont.MAIN_OPTION_TEXT, Color.LIGHT_GRAY, Color.WHITE, null, null, this, game, width / 4f - 100, height / 2f + 165, 200, 40));
    }

    @Override
    public void onComponentClick(String name) {
        switch (name.toLowerCase()) {
            case "singleplayer":
                screenManager.setGameState(GameState.SINGLEPLAYER);
                game.getSpManager().startLevel();
                break;
            case "multiplayer":

                break;
            case "character":
                screenManager.setGameState(GameState.CHARACTER);
                break;
            case "settings":
                ((SettingScreen) screenManager.getScreens().get(GameState.SETTINGS)).update(false);
                screenManager.setGameState(GameState.SETTINGS);
                break;
            case "quit":
                game.stop();
                break;
        }
    }

    @Override
    public void tick() {
        long current = System.nanoTime();
        double tickRatio = 1e9 / 255.0;
        switch (state) {
            case 0:
                delta += (current - previous) / tickRatio;
                previous = current;
                while (delta >= 1) {
                    if (alpha > 0) {
                        alpha--;
                    } else {
                        state++;
                        delta = 0;
                        break;
                    }
                    faderColor = new Color(0, 0, 0, alpha);
                    delta--;
                }
                break;
            case 1:
                for (UIResponsive clickable : clickables.values()) {
                    clickable.tick();
                }
                break;
        }
    }

    @Override
    public void render(Graphics2D graphics) {
        Color prevColor = graphics.getColor();
        Font prevFont = graphics.getFont();

        // Background
        graphics.setColor(Color.GRAY);
        graphics.fillRect(0, 0, width, height);

        // Logo
        int logoStartX = width / 4 - logo.getWidth() / 2, logoStartY = height / 3 - logo.getHeight();
        graphics.drawImage(logo, logoStartX, logoStartY, 400, 250, null);

        // Option Buttons
        for (UIResponsive clickable : clickables.values()) {
            clickable.render(graphics);
        }

        // Fader Overlay
        graphics.setColor(faderColor);
        graphics.fillRect(0, 0, width, height);

        graphics.setColor(prevColor);
        graphics.setFont(prevFont);
    }

    @Override
    public void refresh() {
        previous = System.nanoTime();
    }

}
