package me.thatonerr.slayone.screens;

import me.thatonerr.slayone.Game;
import me.thatonerr.slayone.assets.GameFont;
import me.thatonerr.slayone.ui.UIResponsive;
import me.thatonerr.slayone.ui.UIScreen;
import me.thatonerr.slayone.ui.UISlider;
import me.thatonerr.slayone.managers.ScreenManager;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;

public class AudioSettingScreen extends UIScreen {

    private Font textFont = GameFont.SETTINGS_CONTENT;
    private Map<String, UIResponsive> components = new HashMap<>();

    public AudioSettingScreen(ScreenManager screenManager, Game game) {
        super(screenManager, game, 420, 50, 1005, 800);
        // Word limit 10-12 chars, otherwise it shoots into the slider bar.
        components.put("Music", new UISlider("Music", Color.WHITE, this, game, 715, yStart + 30));
        components.put("Sounds", new UISlider("Sounds", Color.WHITE, this, game, 715, yStart + 80));
    }

    @Override
    public void onComponentClick(String name) {
        
    }

    @Override
    public void tick() {
        for (UIResponsive component : components.values()) {
            component.tick();
        }
    }

    @Override
    public void render(Graphics2D graphics) {
        graphics.setColor(new Color(80, 80, 80, 125));
        graphics.fillRect((int) xStart, (int) yStart, width, height);

        for (String text : components.keySet()) {
            graphics.setFont(textFont);
            graphics.setColor(Color.WHITE);
            UIResponsive component = components.get(text);
            Rectangle2D textBounds = graphics.getFontMetrics(textFont).getStringBounds(text, graphics);
            int textStartX = (int) (xStart + 40), textStartY = (int) (component.getYStart() + component.getHeight() / 2 - textBounds.getCenterY());
            graphics.drawString(text, textStartX, textStartY);
            component.render(graphics);
        }
    }

    @Override
    public void refresh() {

    }

}
