package me.thatonerr.slayone.screens;

import me.thatonerr.slayone.Game;
import me.thatonerr.slayone.ui.UIAbilityMeter;
import me.thatonerr.slayone.ui.UIScreen;
import me.thatonerr.slayone.managers.ScreenManager;
import me.thatonerr.slayone.values.Ability;

import java.awt.*;
import java.util.List;

public class AbilityPage1 extends UIScreen {

    private List<UIAbilityMeter> allMeters;

    public AbilityPage1(List<UIAbilityMeter> allMeters, ScreenManager screenManager, Game game) {
        super(screenManager, game, 420, 50, 1005, 800);
        this.allMeters = allMeters;
        allMeters.add(new UIAbilityMeter(Ability.GRENADE, allMeters, screenManager, game, xStart, yStart, width, 140));
        allMeters.add(new UIAbilityMeter(Ability.SCAN, allMeters, screenManager, game, xStart, yStart + 140, width, 140));
        allMeters.add(new UIAbilityMeter(Ability.SHIELD, allMeters, screenManager, game, xStart, yStart + 280, width, 140));
    }

    @Override
    public void refresh() {
        for (UIAbilityMeter meter : allMeters) {
            meter.refresh();
        }
    }

    @Override
    public void onComponentClick(String name) {

    }

    @Override
    public void tick() {
        for (int i = 0; i < 3; i++) {
            allMeters.get(i).tick();
        }
    }

    @Override
    public void render(Graphics2D graphics) {
        for (int i = 0; i < 3; i++) {
            allMeters.get(i).render(graphics);
        }
    }

}
