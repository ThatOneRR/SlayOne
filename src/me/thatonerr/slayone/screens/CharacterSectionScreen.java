package me.thatonerr.slayone.screens;

import me.thatonerr.slayone.Game;
import me.thatonerr.slayone.assets.GameFont;
import me.thatonerr.slayone.ui.UIScreen;
import me.thatonerr.slayone.ui.UISectionItem;
import me.thatonerr.slayone.info.Character;
import me.thatonerr.slayone.managers.ScreenManager;
import me.thatonerr.slayone.values.CharacterState;
import me.thatonerr.slayone.values.UIState;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class CharacterSectionScreen extends UIScreen {

    private Character character;
    private CharacterScreen screen;
    private List<UISectionItem> items = new ArrayList<>();
    private Font mainFont = GameFont.SETTINGS_SECTION_TITLE;
    private Font subFont = GameFont.SETTINGS_SUBCONTENT;

    public CharacterSectionScreen(CharacterScreen screen, ScreenManager screenManager, Game game) {
        super(screenManager, game, 75, 50, 335, 800);
        this.screen = screen;
        character = Character.getInstance();
        items.add(new UISectionItem("Passive", GameFont.SETTINGS_SECTION_ITEM, Color.LIGHT_GRAY, Color.WHITE, Color.WHITE, null, null, Color.LIGHT_GRAY, this, game, xStart, yStart + 100, width, 70));
        items.add(new UISectionItem("Primary", GameFont.SETTINGS_SECTION_ITEM, Color.LIGHT_GRAY, Color.WHITE, Color.WHITE, null, null, Color.LIGHT_GRAY, this, game, xStart, yStart + 170, width, 70));
        items.add(new UISectionItem("Secondary", GameFont.SETTINGS_SECTION_ITEM, Color.LIGHT_GRAY, Color.WHITE, Color.WHITE, null, null, Color.LIGHT_GRAY, this, game, xStart, yStart + 240, width, 70));
        items.add(new UISectionItem("Skins", GameFont.SETTINGS_SECTION_ITEM, Color.LIGHT_GRAY, Color.WHITE, Color.WHITE, null, null, Color.LIGHT_GRAY, this, game, xStart, yStart + 310, width, 70));
    }

    @Override
    public void refresh() {
        for (UISectionItem screen : items) {
            screen.setState(UIState.IDLE);
        }
        items.get(0).setState(UIState.ACTIVE);
    }

    @Override
    public void onComponentClick(String name) {
        UISectionItem active = null;
        switch (name.toLowerCase()) {
            case "passive":
                active = items.get(0);
                screen.setSectionState(CharacterState.PASSIVE);
                break;
            case "primary":
                active = items.get(1);
                screen.setSectionState(CharacterState.ABILITY_1);
                break;
            case "secondary":
                active = items.get(2);
                screen.setSectionState(CharacterState.ABILITY_2);
                break;
            case "skins":
                active = items.get(3);
                screen.setSectionState(CharacterState.SKINS);
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
        graphics.setColor(new Color(120, 120, 120, 125));
        graphics.fillRect((int) xStart, (int) yEnd - 100, width, 100);

        String text = "Skill Points: " + character.getPoints() + "/12";
        Rectangle2D textBounds = graphics.getFontMetrics(subFont).getStringBounds(text, graphics);
        int textStartX = (int) (xStart + width / 2 - textBounds.getCenterX()), textStartY = (int) (yEnd - 50 - textBounds.getCenterY());
        graphics.setColor(Color.WHITE);
        graphics.setFont(subFont);
        graphics.drawString(text, textStartX, textStartY);

        Rectangle2D textBounds2 = graphics.getFontMetrics(mainFont).getStringBounds("Character", graphics);
        int textStartX2 = (int) (xStart + width / 2 - textBounds2.getCenterX()), textStartY2 = (int) (yStart + 50 - textBounds2.getCenterY());
        graphics.setColor(Color.WHITE);
        graphics.setFont(mainFont);
        graphics.drawString("Character", textStartX2, textStartY2);

        for (UISectionItem item : items) {
            item.render(graphics);
        }
    }

}
