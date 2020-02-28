package me.thatonerr.slayone.ui;

import me.thatonerr.slayone.Game;
import me.thatonerr.slayone.abilities.GrenadeAbility;
import me.thatonerr.slayone.abilities.ScanAbility;
import me.thatonerr.slayone.abilities.ShieldAbility;
import me.thatonerr.slayone.assets.GameFont;
import me.thatonerr.slayone.assets.Sprite;
import me.thatonerr.slayone.abilities.AbilityInfo;
import me.thatonerr.slayone.info.Character;
import me.thatonerr.slayone.managers.ScreenManager;
import me.thatonerr.slayone.values.Ability;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UIAbilityMeter extends UIScreen {

    private BufferedImage icon;
    private Character character;
    private int active = 0, viewing;
    private String name;
    private Ability ability;
    private List<UIAbilityMeter> allMeters;
    private Map<String, UIAbilitySubMeter> subMeters = new HashMap<>();
    private UICheckbox checkbox;
    private AbilityInfo abilityInfo;

    private Font mainFont = GameFont.SETTINGS_CONTENT;
    private Font headerFont = GameFont.SETTINGS_SECTION_TITLE;

    public UIAbilityMeter(Ability ability, List<UIAbilityMeter> allMeters, ScreenManager screenManager, Game game, float x, float y, int width, int height) {
        super(screenManager, game, x, y, width, height);
        this.allMeters = allMeters;
        this.ability = ability;
        character = Character.getInstance();
        name = ability.getName();
        switch (ability) {
            case GRENADE:
                subMeters.put("Damage", new UIAbilitySubMeter(allMeters, "Damage", 0, null, screenManager, game, 1250, yStart + 35, 300, 30));
                subMeters.put("Range", new UIAbilitySubMeter(allMeters, "Range", 0, null, screenManager, game, 1250, yStart + 75, 300, 30));
                icon = Sprite.ICON_GRENADE;
                break;
            case SCAN:
                subMeters.put("Duration", new UIAbilitySubMeter(allMeters, "Duration", 0, null, screenManager, game, 1250, yStart + 55, 300, 30));
                icon = Sprite.ICON_SCAN;
                break;
            case SHIELD:
                icon = Sprite.ICON_SHIELD;
                break;
        }
        checkbox = new UICheckbox("Checkbox", this, game, xStart + 10, yStart + 10, 30, 30);
    }

    @Override
    public void refresh() {
        checkbox.setActive(active != 0);
        checkbox.setDisabled(character.getPoints() == 0 && !checkbox.isActive() || active != 0 && active != viewing);
        switch (viewing) {
            case 1:
                if (character.getAbility1() != abilityInfo && character.getAbility1() != null || character.getAbility2() != null && character.getAbility2() == abilityInfo) {
                    checkbox.setDisabled(true);
                }
                break;
            case 2:
                if (character.getAbility2() != abilityInfo && character.getAbility2() != null || character.getAbility1() != null && character.getAbility1() == abilityInfo) {
                    checkbox.setDisabled(true);
                }
                break;
        }
        for (UIAbilitySubMeter meter : subMeters.values()) {
            meter.refresh();
        }
    }

    @Override
    public void onComponentClick(String name) {
        switch (name) {
            case "Checkbox":
                if (active == 0) {
                    active = viewing;
                    character.setPoints(character.getPoints() - 1);
                    switch (ability) {
                        case GRENADE:
                            abilityInfo = new GrenadeAbility();
                            break;
                        case SCAN:
                            abilityInfo = new ScanAbility();
                            break;
                        case SHIELD:
                            abilityInfo = new ShieldAbility();
                            break;
                    }
                    switch (viewing) {
                        case 1:
                            character.setAbility1(abilityInfo);
                            break;
                        case 2:
                            character.setAbility2(abilityInfo);
                            break;
                    }
                    for (UIAbilitySubMeter meter : subMeters.values()) {
                        meter.setAbilityInfo(abilityInfo);
                    }
                } else {
                    active = 0;
                    switch (viewing) {
                        case 1:
                            character.setAbility1(null);
                            break;
                        case 2:
                            character.setAbility2(null);
                            break;
                    }
                    character.setPoints(character.getPoints() + abilityInfo.getAllPoints());
                    abilityInfo = null;
                    for (UIAbilitySubMeter meter : subMeters.values()) {
                        meter.setAbilityInfo(null);
                    }
                }
                break;
        }
        for (UIAbilityMeter meter : allMeters) {
            meter.refresh();
        }
    }

    @Override
    public void tick() {
        if (active != 0 && active != viewing) {
            return;
        }
        switch (viewing) {
            case 1:
                if (character.getAbility1() != null && character.getAbility1() != abilityInfo && character.getAbility2() != abilityInfo) return;
            case 2:
                if (character.getAbility2() != null && character.getAbility2() != abilityInfo && character.getAbility1() != abilityInfo) return;
        }
        checkbox.tick();
        for (UIAbilitySubMeter subMeter : subMeters.values()) {
            subMeter.tick();
        }
    }

    @Override
    public void render(Graphics2D graphics) {
        graphics.setColor(new Color(120, 120, 120, 120));
        graphics.fillRect((int) xStart, (int) yStart, width, height);

        graphics.drawImage(icon, (int) (xStart + 50), (int) (yStart + height / 2 - 25), 50, 50, null);

        Rectangle2D textBounds = graphics.getFontMetrics(mainFont).getStringBounds(name, graphics);
        int statusStartX = (int) (xStart + 115), statusStartY = (int) (yStart + height / 2 - textBounds.getCenterY());
        graphics.setColor(Color.WHITE);
        graphics.setFont(mainFont);
        graphics.drawString(name, statusStartX, statusStartY);

        for (UIAbilitySubMeter subMeter : subMeters.values()) {
            subMeter.render(graphics);
        }

        if (active == 0) {
            graphics.setColor(new Color(120, 120, 120, 60));
            graphics.fillRect((int) xStart, (int) yStart, width, height);
        } else if (active != viewing) {
            graphics.setColor(new Color(120, 120, 120, 200));
            graphics.fillRect((int) xStart, (int) yStart, width, height);
            Rectangle2D textBounds2 = graphics.getFontMetrics(headerFont).getStringBounds("Ability " + active, graphics);
            int statusStartX2 = (int) (xStart + width / 2 - textBounds2.getCenterX()), statusStartY2 = (int) (yStart + height / 2 - textBounds2.getCenterY());
            graphics.setColor(Color.WHITE);
            graphics.setFont(headerFont);
            graphics.drawString("Ability " + active, statusStartX2, statusStartY2);
        }

        checkbox.render(graphics);
    }

    public int getViewing() {
        return viewing;
    }

    public void setViewing(int viewing) {
        this.viewing = viewing;
    }

}
