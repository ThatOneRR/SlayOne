package me.thatonerr.slayone.ui;

import me.thatonerr.slayone.Game;
import me.thatonerr.slayone.assets.GameFont;
import me.thatonerr.slayone.assets.Sprite;
import me.thatonerr.slayone.info.Character;
import me.thatonerr.slayone.info.Passive;
import me.thatonerr.slayone.managers.ScreenManager;
import me.thatonerr.slayone.screens.PassiveScreen;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIStatMeter extends UIScreen {

    private Character character;
    private Passive passive;
    private PassiveScreen screen;
    private String name;
    private int amount, max;
    private UIButton add, remove;
    private BufferedImage empty = Sprite.STAT_METER_EMPTY;
    private BufferedImage filled = Sprite.STAT_METER_FILLED;

    public UIStatMeter(String name, int amount, int max, PassiveScreen screen, ScreenManager screenManager, Game game, float x, float y, int width, int height) {
        super(screenManager, game, x, y, width, height);
        this.screen = screen;
        character = Character.getInstance();
        passive = character.getPassive();
        this.name = name;
        this.amount = amount;
        this.max = max;
        remove = new UIButton("-", GameFont.METER_BUTTONS, Color.LIGHT_GRAY, Color.WHITE, Color.GRAY, Color.DARK_GRAY, this, game, x, y, 25, 30);
        add = new UIButton("+", GameFont.METER_BUTTONS, Color.LIGHT_GRAY, Color.WHITE, Color.GRAY, Color.DARK_GRAY, this, game, x + 25 + 30 + 240, y, 25, 30);
    }

    @Override
    public void refresh() {
        remove.setDisabled(amount == 0);
        switch (name) {
            case "Agility":
                passive.setAgility(amount);
                break;
            case "Health":
                passive.setHealth(amount);
                break;
            case "Intelligence":
                passive.setIntel(amount);
                break;
            case "Regeneration":
                passive.setRegen(amount);
                break;
            case "Lifesteal":
                passive.setLifesteal(amount);
                break;
        }
    }

    @Override
    public void onComponentClick(String name) {
        switch (name) {
            case "+":
                amount++;
                character.setPoints(character.getPoints() - 1);
                break;
            case "-":
                amount--;
                character.setPoints(character.getPoints() + 1);
                break;
        }
        refresh();
    }

    @Override
    public void tick() {
        add.setDisabled(amount == max || character.getPoints() == 0);
        add.tick();
        remove.tick();
    }

    @Override
    public void render(Graphics2D graphics) {
        add.render(graphics);
        remove.render(graphics);

        int startX = 0;
        for (int i = 0; i < amount; i++) {
            graphics.drawImage(filled, (int) xStart + 40 + startX, (int) (yStart + height / 2 - 15), 15, 30, null);
            startX += 25;
        }
        for (int i = 0; i < (max - amount); i++) {
            graphics.drawImage(empty, (int) xStart + 40 + startX, (int) (yStart + height / 2 - 15), 15, 30, null);
            startX += 25;
        }
    }

}
