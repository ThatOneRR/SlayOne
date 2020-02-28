package me.thatonerr.slayone.screens;

import me.thatonerr.slayone.Game;
import me.thatonerr.slayone.assets.GameFont;
import me.thatonerr.slayone.ui.UIButton;
import me.thatonerr.slayone.ui.UIScreen;
import me.thatonerr.slayone.managers.ScreenManager;
import me.thatonerr.slayone.values.GameState;
import me.thatonerr.slayone.values.SettingState;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class SettingScreen extends UIScreen {

    private boolean ingame;
    private UIScreen sectionScreen;
    private UIButton closeButton;
    private SettingState sectionState;
    private Map<SettingState, UIScreen> contentScreens = new HashMap<>();

    public SettingScreen(ScreenManager screenManager, Game game, float x, float y) {
        super(screenManager, game, x, y);
        closeButton = new UIButton("x", GameFont.MAIN_OPTION_TEXT, Color.LIGHT_GRAY, Color.GRAY, null, null, this, game, 10, 5, 30, 30);
        sectionScreen = new SettingSectionScreen(this, screenManager, game);
        contentScreens.put(SettingState.AUDIO, new AudioSettingScreen(screenManager, game));
        contentScreens.put(SettingState.CONTROLS, new ControlSettingScreen(screenManager, game));
    }

    @Override
    public void onComponentClick(String name) {
        switch (name.toLowerCase()) {
            case "x":
                screenManager.setGameState(GameState.MAIN_MENU);
                break;
        }
    }

    @Override
    public void tick() {
        closeButton.tick();
        sectionScreen.tick();
        contentScreens.get(sectionState).tick();
    }

    @Override
    public void render(Graphics2D graphics) {
        if (ingame) {
            graphics.setColor(new Color(180, 180, 180, 180));
            graphics.fillRect((int) xStart, (int) yStart, width, height);
        }
        closeButton.render(graphics);
        sectionScreen.render(graphics);
        contentScreens.get(sectionState).render(graphics);
    }

    @Override
    public void refresh() {
        setSectionState(SettingState.AUDIO);
        sectionScreen.refresh();
    }

    public void update(boolean ingame) {
        this.ingame = ingame;
    }

    public SettingState getSectionState() {
        return sectionState;
    }

    public void setSectionState(SettingState sectionState) {
        this.sectionState = sectionState;
        UIScreen screen = contentScreens.get(sectionState);
        if (screen != null) screen.refresh();
    }

    public boolean isIngame() {
        return ingame;
    }

}
