package me.thatonerr.slayone.screens;

import me.thatonerr.slayone.Game;
import me.thatonerr.slayone.assets.GameFont;
import me.thatonerr.slayone.assets.Sprite;
import me.thatonerr.slayone.ui.UIStatMeter;
import me.thatonerr.slayone.ui.UIScreen;
import me.thatonerr.slayone.managers.ScreenManager;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class PassiveScreen extends UIScreen {

    private Map<String, UIStatMeter> meters = new HashMap<>();
    private Map<String, BufferedImage> icons = new HashMap<>();
    private Font mainFont = GameFont.SETTINGS_CONTENT;

    public PassiveScreen(ScreenManager screenManager, Game game) {
        super(screenManager, game, 420, 50, 1005, 800);

        meters.put("Agility", new UIStatMeter("Agility", 0, 10, this, screenManager, game, 1050, yStart + 35, 300, 30));
        meters.put("Health", new UIStatMeter("Health", 0, 10, this, screenManager, game, 1050, yStart + 85, 300, 30));
        meters.put("Intelligence", new UIStatMeter("Intelligence", 0, 10, this, screenManager, game, 1050, yStart + 135, 300, 30));
        meters.put("Regeneration", new UIStatMeter("Regeneration", 0, 10, this, screenManager, game, 1050, yStart + 185, 300, 30));
        meters.put("Lifesteal", new UIStatMeter("Lifesteal", 0, 10, this, screenManager, game, 1050, yStart + 235, 300, 30));

        icons.put("Agility", Sprite.ICON_AGILITY);
        icons.put("Health", Sprite.ICON_HEALTH);
        icons.put("Intelligence", Sprite.ICON_INTEL);
        icons.put("Regeneration", Sprite.ICON_REGEN);
        icons.put("Lifesteal", Sprite.ICON_LIFESTEAL);
    }

    @Override
    public void refresh() {
        for (UIStatMeter meter : meters.values()) {
            meter.refresh();
        }
    }

    @Override
    public void onComponentClick(String name) {

    }

    @Override
    public void tick() {
        for (UIStatMeter meter : meters.values()) {
            meter.tick();
        }
    }

    @Override
    public void render(Graphics2D graphics) {
        graphics.setColor(new Color(80, 80, 80, 125));
        graphics.fillRect((int) xStart, (int) yStart, width, height);

        for (String name : meters.keySet()) {
            UIStatMeter meter = meters.get(name);
            BufferedImage icon = icons.get(name);

            graphics.drawImage(icon, (int) (xStart + 10), (int) (meter.getYStart() + meter.getHeight() / 2 - 25), 50, 50, null);

            Rectangle2D textBounds = graphics.getFontMetrics(mainFont).getStringBounds(name, graphics);
            int textStartX = (int) (xStart + 80), textStartY = (int) (meter.getYStart() + meter.getHeight() / 2 - textBounds.getCenterY());
            graphics.setFont(mainFont);
            graphics.setColor(Color.WHITE);
            graphics.drawString(name, textStartX, textStartY);

            meter.render(graphics);
        }
    }

}
