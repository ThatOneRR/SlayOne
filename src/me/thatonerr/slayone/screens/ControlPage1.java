package me.thatonerr.slayone.screens;

import me.thatonerr.slayone.Game;
import me.thatonerr.slayone.assets.GameFont;
import me.thatonerr.slayone.ui.UIKeySwap;
import me.thatonerr.slayone.ui.UIScreen;
import me.thatonerr.slayone.managers.ScreenManager;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.*;
import java.util.List;

public class ControlPage1 extends UIScreen {

    private Font mainFont = GameFont.SETTINGS_CONTENT;
    private int page = 1;
    private Map<String, UIKeySwap> pageKeySwaps = new HashMap<>();

    public ControlPage1(Map<String, UIKeySwap> keySwaps, ScreenManager screenManager, Game game, float x, float y, int width, int height) {
        super(screenManager, game, x, y, width, height);

        pageKeySwaps.put("Shoot", new UIKeySwap("Shoot", keySwaps, page, this, game, xEnd - 30 - 150, yStart + 15, 150, 70));
        pageKeySwaps.put("Move Up", new UIKeySwap("Up", keySwaps, page, this, game, xEnd - 30 - 150, yStart + 100, 150, 70));
        pageKeySwaps.put("Move Left", new UIKeySwap("Left", keySwaps, page, this, game, xEnd - 30 - 150, yStart + 185, 150, 70));
        pageKeySwaps.put("Move Down", new UIKeySwap("Down", keySwaps, page, this, game, xEnd - 30 - 150, yStart + 270, 150, 70));
        pageKeySwaps.put("Move Right", new UIKeySwap("Right", keySwaps, page, this, game, xEnd - 30 - 150, yStart + 355, 150, 70));
        pageKeySwaps.put("Jump", new UIKeySwap("Jump", keySwaps, page, this, game, xEnd - 30 - 150, yStart + 440, 150, 70));
        pageKeySwaps.put("Reload", new UIKeySwap("Reload", keySwaps, page, this, game, xEnd - 30 - 150, yStart + 525, 150, 70));
        pageKeySwaps.put("Scope", new UIKeySwap("Scope", keySwaps, page, this, game, xEnd - 30 - 150, yStart + 610, 150, 70));

        List<String> keys = new ArrayList<>(pageKeySwaps.keySet());
        List<UIKeySwap> values = new ArrayList<>(pageKeySwaps.values());
        for (int i = 0; i < 8; i++) {
            keySwaps.put(keys.get(i), values.get(i));
        }
    }

    @Override
    public void refresh() {
        for (UIKeySwap keySwap : pageKeySwaps.values()) {
            keySwap.refresh();
        }
    }

    @Override
    public void onComponentClick(String name) {

    }

    @Override
    public void tick() {
        for (UIKeySwap keySwap : pageKeySwaps.values()) {
            keySwap.tick();
        }
    }

    @Override
    public void render(Graphics2D graphics) {
        for (String keyName : pageKeySwaps.keySet()) {
            UIKeySwap keySwap = pageKeySwaps.get(keyName);
            graphics.setColor(Color.WHITE);
            graphics.setFont(mainFont);
            Rectangle2D textBounds = graphics.getFontMetrics(mainFont).getStringBounds(keyName, graphics);
            int textStartX = (int) xStart + 30, textStartY = (int) (keySwap.getYStart() + keySwap.getHeight() / 2 - textBounds.getCenterY());
            graphics.drawString(keyName, textStartX, textStartY);
            keySwap.render(graphics);
        }
    }

}
