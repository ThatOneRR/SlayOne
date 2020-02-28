package me.thatonerr.slayone.screens;

import me.thatonerr.slayone.Game;
import me.thatonerr.slayone.assets.GameFont;
import me.thatonerr.slayone.ui.UIButton;
import me.thatonerr.slayone.ui.UIKeySwap;
import me.thatonerr.slayone.ui.UIScreen;
import me.thatonerr.slayone.managers.ScreenManager;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControlSettingScreen extends UIScreen {

    private int currentPage = 1;

    private List<UIScreen> pages = new ArrayList<>();
    private Map<String, UIKeySwap> keySwaps = new HashMap<>();

    private Font mainFont = GameFont.SETTINGS_SUBCONTENT;

    private UIButton leftBtn, rightBtn;

    public ControlSettingScreen(ScreenManager screenManager, Game game) {
        super(screenManager, game, 420, 50, 1005, 800);
        // Each page contains up to 8 keybind controls!
        pages.add(new ControlPage1(keySwaps, screenManager, game, 420, 50, 1000, 725));
        pages.add(new ControlPage2(keySwaps, screenManager, game, 420, 50, 1000, 725));

        leftBtn = new UIButton("<<", GameFont.SETTINGS_CONTENT, Color.LIGHT_GRAY, Color.WHITE, Color.GRAY, Color.DARK_GRAY, this, game, xStart + 20, yStart + 740, 100, 45);
        rightBtn = new UIButton(">>", GameFont.SETTINGS_CONTENT, Color.LIGHT_GRAY, Color.WHITE, Color.GRAY, Color.DARK_GRAY, this, game, xEnd - 120, yStart + 740, 100, 45);
    }

    @Override
    public void onComponentClick(String name) {
        UIKeySwap.current = null;
        pages.get(currentPage - 1).refresh();
        switch (name) {
            case "<<":
                currentPage--;
                break;
            case ">>":
                currentPage++;
                break;
        }
        if (currentPage < 1) currentPage = 1;
        if (currentPage > pages.size()) currentPage = pages.size();
        leftBtn.setDisabled(currentPage == 1);
        rightBtn.setDisabled(currentPage == pages.size());
    }

    @Override
    public void tick() {
        pages.get(currentPage - 1).tick();
        leftBtn.tick();
        rightBtn.tick();
    }

    @Override
    public void render(Graphics2D graphics) {
        graphics.setColor(new Color(80, 80, 80, 125));
        graphics.fillRect((int) xStart, (int) yStart, width, height);

        pages.get(currentPage - 1).render(graphics);

        graphics.setColor(new Color(150, 150, 150, 125));
        graphics.fillRect((int) xStart, (int) yStart + 725, width, 75);
        leftBtn.render(graphics);
        rightBtn.render(graphics);
        graphics.setColor(Color.WHITE);
        String text = "Page " + currentPage + "/" + pages.size();
        Rectangle2D textBounds = graphics.getFontMetrics(mainFont).getStringBounds(text, graphics);
        int textStartX = (int) (xStart + width / 2 - textBounds.getCenterX()), textStartY = (int) (yStart + 725 + 75 / 2 - textBounds.getCenterY());
        graphics.drawString(text, textStartX, textStartY);
    }

    @Override
    public void refresh() {
        leftBtn.setDisabled(true);
        rightBtn.setDisabled(false);
        currentPage = 1;
        for (UIKeySwap keySwap : keySwaps.values()) {
            keySwap.refresh();
        }
    }

}
