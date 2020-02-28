package me.thatonerr.slayone.ui;

import me.thatonerr.slayone.Game;
import me.thatonerr.slayone.abilities.GrenadeAbility;
import me.thatonerr.slayone.abilities.ScanAbility;
import me.thatonerr.slayone.assets.GameFont;
import me.thatonerr.slayone.assets.Sprite;
import me.thatonerr.slayone.abilities.AbilityInfo;
import me.thatonerr.slayone.info.Character;
import me.thatonerr.slayone.managers.ScreenManager;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.List;

public class UIAbilitySubMeter extends UIScreen {

    private String name;
    private Character character;
    private AbilityInfo abilityInfo;
    private int amount;
    private UIButton add, remove;
    private BufferedImage empty = Sprite.STAT_METER_EMPTY;
    private BufferedImage filled = Sprite.STAT_METER_FILLED;
    private List<UIAbilityMeter> allMeters;

    private Font mainFont = GameFont.SETTINGS_SUBCONTENT;

    public UIAbilitySubMeter(List<UIAbilityMeter> allMeters, String name, int amount, AbilityInfo abilityInfo, ScreenManager screenManager, Game game, float xStart, float yStart, int width, int height) {
        super(screenManager, game, xStart, yStart, width, height);
        this.allMeters = allMeters;
        character = Character.getInstance();
        this.abilityInfo = abilityInfo;
        this.name = name;
        this.amount = amount;
        remove = new UIButton("-", GameFont.METER_BUTTONS, Color.LIGHT_GRAY, Color.WHITE, Color.GRAY, Color.DARK_GRAY, this, game, xStart, yStart, 25, 30);
        add = new UIButton("+", GameFont.METER_BUTTONS, Color.LIGHT_GRAY, Color.WHITE, Color.GRAY, Color.DARK_GRAY, this, game, xStart + 25 + 30 + 65, yStart, 25, 30);
    }

    @Override
    public void refresh() {
        if (abilityInfo == null) {
            amount = 0;
            return;
        }
        if (abilityInfo instanceof GrenadeAbility) {
            GrenadeAbility grenadeAbility = (GrenadeAbility) abilityInfo;
            switch (name) {
                case "Damage":
                    amount = grenadeAbility.getDamage();
                    break;
                case "Range":
                    amount = grenadeAbility.getRange();
                    break;
            }
        } else if (abilityInfo instanceof ScanAbility) {
            ScanAbility scanAbility = (ScanAbility) abilityInfo;
            switch (name) {
                case "Duration":
                    amount = scanAbility.getDuration();
                    break;
            }
        }
    }

    @Override
    public void onComponentClick(String name) {
        int amount = -1;
        if (name.equalsIgnoreCase("+")) amount = 1;
        if (abilityInfo instanceof GrenadeAbility) {
            GrenadeAbility grenadeAbility = (GrenadeAbility) abilityInfo;
            switch (this.name) {
                case "Damage":
                    grenadeAbility.setDamage(grenadeAbility.getDamage() + amount);
                    break;
                case "Range":
                    grenadeAbility.setRange(grenadeAbility.getRange() + amount);
                    break;
            }
        }  else if (abilityInfo instanceof ScanAbility) {
            ScanAbility scanAbility = (ScanAbility) abilityInfo;
            switch (this.name) {
                case "Duration":
                    scanAbility.setDuration(scanAbility.getDuration() + amount);
                    break;
            }
        }
        character.setPoints(character.getPoints() - amount);
        this.amount += amount;
        refresh();
        for (UIAbilityMeter meter : allMeters) {
            meter.refresh();
        }
    }

    @Override
    public void tick() {
        add.setDisabled(amount == 3 || character.getPoints() == 0 || abilityInfo == null);
        remove.setDisabled(amount == 0 || abilityInfo == null);
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
        for (int i = 0; i < (3 - amount); i++) {
            graphics.drawImage(empty, (int) xStart + 40 + startX, (int) (yStart + height / 2 - 15), 15, 30, null);
            startX += 25;
        }

        Rectangle2D textBounds = graphics.getFontMetrics(mainFont).getStringBounds(name, graphics);
        int statusStartX = (int) (xStart - 20 - textBounds.getWidth()), statusStartY = (int) (yStart + height / 2 - textBounds.getCenterY());
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.setFont(mainFont);
        graphics.drawString(name, statusStartX, statusStartY);
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public AbilityInfo getAbilityInfo() {
        return abilityInfo;
    }

    public void setAbilityInfo(AbilityInfo abilityInfo) {
        this.abilityInfo = abilityInfo;
    }

}
