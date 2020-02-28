package me.thatonerr.slayone.screens;

import me.thatonerr.slayone.Game;
import me.thatonerr.slayone.assets.GameFont;
import me.thatonerr.slayone.ui.UIKeySwap;
import me.thatonerr.slayone.ui.UIScreen;
import me.thatonerr.slayone.managers.ScreenManager;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControlPage2 extends UIScreen {

    private Font mainFont = GameFont.SETTINGS_CONTENT;
    private int page = 2;
    private Map<String, UIKeySwap> pageKeySwaps = new HashMap<>();

    public ControlPage2(Map<String, UIKeySwap> keySwaps, ScreenManager screenManager, Game game, float x, float y, int width, int height) {
        super(screenManager, game, x, y, width, height);

        pageKeySwaps.put("Ability 1", new UIKeySwap("Ab1", keySwaps, page, this, game, xEnd - 30 - 150, yStart + 15, 150, 70));
        pageKeySwaps.put("Ability 2", new UIKeySwap("Ab2", keySwaps, page, this, game, xEnd - 30 - 150, yStart + 100, 150, 70));

        List<String> keys = new ArrayList<>(pageKeySwaps.keySet());
        List<UIKeySwap> values = new ArrayList<>(pageKeySwaps.values());
        for (int i = 0; i < 2; i++) {
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
