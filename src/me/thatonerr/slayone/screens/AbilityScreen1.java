package me.thatonerr.slayone.screens;

import me.thatonerr.slayone.Game;
import me.thatonerr.slayone.assets.GameFont;
import me.thatonerr.slayone.ui.UIAbilityMeter;
import me.thatonerr.slayone.ui.UIButton;
import me.thatonerr.slayone.ui.UIKeySwap;
import me.thatonerr.slayone.ui.UIScreen;
import me.thatonerr.slayone.info.Character;
import me.thatonerr.slayone.managers.ScreenManager;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class AbilityScreen1 extends UIScreen {

    private int currentPage = 1;
    private Character character;
    private List<UIScreen> pages;
    private List<UIAbilityMeter> abilityMeters = new ArrayList<>();

    private Font mainFont = GameFont.SETTINGS_SUBCONTENT;

    private UIButton leftBtn, rightBtn;

    public AbilityScreen1(List<UIScreen> pages, List<UIAbilityMeter> abilityMeters, ScreenManager screenManager, Game game) {
        super(screenManager, game, 420, 50, 1005, 800);
        character = Character.getInstance();
        this.abilityMeters = abilityMeters;
        this.pages = pages;
        leftBtn = new UIButton("<<", GameFont.SETTINGS_CONTENT, Color.LIGHT_GRAY, Color.WHITE, Color.GRAY, Color.DARK_GRAY, this, game, xStart + 20, yStart + 740, 100, 45);
        rightBtn = new UIButton(">>", GameFont.SETTINGS_CONTENT, Color.LIGHT_GRAY, Color.WHITE, Color.GRAY, Color.DARK_GRAY, this, game, xEnd - 120, yStart + 740, 100, 45);
    }

    @Override
    public void refresh() {
        currentPage = 1;
        for (UIAbilityMeter abilityMeter : abilityMeters) {
            abilityMeter.setViewing(1);
        }
        for (UIScreen screen : pages) {
            screen.refresh();
        }
        leftBtn.setDisabled(currentPage == 1);
        rightBtn.setDisabled(currentPage == pages.size());
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
        graphics.setFont(mainFont);
        String text = "Page " + currentPage + "/" + pages.size();
        Rectangle2D textBounds = graphics.getFontMetrics(mainFont).getStringBounds(text, graphics);
        int textStartX = (int) (xStart + width / 2 - textBounds.getCenterX()), textStartY = (int) (yStart + 725 + 75 / 2 - textBounds.getCenterY());
        graphics.drawString(text, textStartX, textStartY);
    }

}
