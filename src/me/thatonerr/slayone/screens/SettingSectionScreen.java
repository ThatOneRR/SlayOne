package me.thatonerr.slayone.screens;

import me.thatonerr.slayone.Game;
import me.thatonerr.slayone.assets.GameFont;
import me.thatonerr.slayone.ui.UIScreen;
import me.thatonerr.slayone.ui.UISectionItem;
import me.thatonerr.slayone.managers.ScreenManager;
import me.thatonerr.slayone.values.SettingState;
import me.thatonerr.slayone.values.UIState;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class SettingSectionScreen extends UIScreen {

    private SettingScreen screen;
    private List<UISectionItem> items = new ArrayList<>();
    private Font mainFont = GameFont.SETTINGS_SECTION_TITLE;

    public SettingSectionScreen(SettingScreen screen, ScreenManager screenManager, Game game) {
        super(screenManager, game, 75, 50, 335, 800);
        this.screen = screen;
        items.add(new UISectionItem("Audio", GameFont.SETTINGS_SECTION_ITEM, Color.LIGHT_GRAY, Color.WHITE, Color.WHITE, null, null, Color.LIGHT_GRAY, this, game, xStart, yStart + 100, width, 70));
        items.add(new UISectionItem("Controls", GameFont.SETTINGS_SECTION_ITEM, Color.LIGHT_GRAY, Color.WHITE, Color.WHITE, null, null, Color.LIGHT_GRAY, this, game, xStart, yStart + 170, width, 70));
    }

    @Override
    public void onComponentClick(String name) {
        UISectionItem active = null;
        switch (name.toLowerCase()) {
            case "audio":
                active = items.get(0);
                screen.setSectionState(SettingState.AUDIO);
                break;
            case "controls":
                active = items.get(1);
                screen.setSectionState(SettingState.CONTROLS);
                break;
        }
        for (UISectionItem item : items) {
            if (item != active) item.setState(UIState.IDLE);
        }
    }

    @Override
    public void tick() {
        for (UISectionItem item : items) {
            item.tick();
        }
    }

    @Override
    public void render(Graphics2D graphics) {
        graphics.setColor(new Color(80, 80, 80, 125));
        graphics.fillRect((int) xStart, (int) yStart, width, height);
        graphics.fillRect((int) xStart, (int) yStart, width, 100);

        Rectangle2D textBounds = graphics.getFontMetrics(mainFont).getStringBounds("Settings", graphics);
        int textStartX = (int) (xStart + width / 2 - textBounds.getCenterX()), textStartY = (int) (yStart + 50 - textBounds.getCenterY());
        graphics.setColor(Color.WHITE);
        graphics.setFont(mainFont);
        graphics.drawString("Settings", textStartX, textStartY);

        for (UISectionItem item : items) {
            item.render(graphics);
        }
    }

    @Override
    public void refresh() {
        for (UISectionItem screen : items) {
            screen.setState(UIState.IDLE);
        }
        items.get(0).setState(UIState.ACTIVE);
    }

}
