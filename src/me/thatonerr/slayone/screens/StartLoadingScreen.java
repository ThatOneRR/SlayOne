package me.thatonerr.slayone.screens;

import me.thatonerr.slayone.Game;
import me.thatonerr.slayone.assets.GameFont;
import me.thatonerr.slayone.assets.Sprite;
import me.thatonerr.slayone.ui.UIScreen;
import me.thatonerr.slayone.info.Character;
import me.thatonerr.slayone.managers.ScreenManager;
import me.thatonerr.slayone.utilities.GunPopulator;
import me.thatonerr.slayone.utilities.SkinPopulator;
import me.thatonerr.slayone.utilities.TilePopulator;
import me.thatonerr.slayone.values.GameState;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Map;

import static me.thatonerr.slayone.utilities.Utils.*;

public class StartLoadingScreen extends UIScreen implements Runnable {

    private Map<GameState, UIScreen> screens;
    private Thread thread = new Thread(this);

    private String status = "Initialising game";
    private double delta = 0;
    private long previous;
    private int state = 0, progress = 0, alpha = 255;
    private Color faderColor = new Color(0, 0, 0, alpha);

    private Font statusFont = GameFont.START_LOADING_TEXT;
    private BufferedImage logo = Sprite.LOGO;
    private BufferedImage outline = Sprite.START_LOADING_BAR_OUTLINE;
    private BufferedImage leftFill = Sprite.START_LOADING_BAR_LEFT_FILL;
    private BufferedImage midFill = Sprite.START_LOADING_BAR_MID_FILL;
    private BufferedImage rightFill = Sprite.START_LOADING_BAR_RIGHT_FILL;

    public StartLoadingScreen(ScreenManager screenManager, Game game, float x, float y) {
        super(screenManager, game, x, y);
        screens = screenManager.getScreens();
        previous = System.nanoTime();
    }

    @Override
    public void run() {
        status = "Loading text fonts";
        GameFont.MAIN_OPTION_TEXT = getFontResource("/fonts/corbel.ttf", 35);
        GameFont.SETTINGS_SECTION_ITEM = getFontResource("/fonts/corbel.ttf", 35);
        GameFont.SETTINGS_CONTENT = getFontResource("/fonts/corbel.ttf", 30);
        GameFont.SETTINGS_SECTION_TITLE = getFontResource("/fonts/candara.ttf", 40);
        GameFont.SETTINGS_SUBCONTENT = getFontResource("/fonts/corbel.ttf", 25);
        GameFont.METER_BUTTONS = getFontResource("/fonts/candara.ttf", 15);
        progress++;

        status = "Loading game sprites";
        Sprite.SETTINGS_SLIDER_OUTLINE = getImageResource("/sprites/ui/slider_bar/outline.png");
        Sprite.SETTINGS_SLIDER_LEFT_FILL = getImageResource("/sprites/ui/slider_bar/left_fill.png");
        Sprite.SETTINGS_SLIDER_MID_FILL = getImageResource("/sprites/ui/slider_bar/mid_fill.png");
        Sprite.SETTINGS_SLIDER_THUMB_IDLE = getImageResource("/sprites/ui/slider_bar/thumb_idle.png");
        Sprite.SETTINGS_SLIDER_THUMB_HOVER = getImageResource("/sprites/ui/slider_bar/thumb_hover.png");
        Sprite.SETTINGS_SLIDER_THUMB_ACTIVE = getImageResource("/sprites/ui/slider_bar/thumb_active.png");
        Sprite.CHECKBOX_EMPTY = getImageResource("/sprites/ui/checkbox/empty.png");
        Sprite.CHECKBOX_EMPTY_HOVER = getImageResource("/sprites/ui/checkbox/empty_hover.png");
        Sprite.CHECKBOX_FILLED = getImageResource("/sprites/ui/checkbox/filled.png");
        Sprite.CHECKBOX_FILLED_HOVER = getImageResource("/sprites/ui/checkbox/filled_hover.png");
        Sprite.STAT_METER_EMPTY = getImageResource("/sprites/ui/stat_meter/empty.png");
        Sprite.STAT_METER_FILLED = getImageResource("/sprites/ui/stat_meter/filled.png");
        Sprite.ICON_AGILITY = getImageResource("/sprites/icons/agility.png");
        Sprite.ICON_HEALTH = getImageResource("/sprites/icons/health.png");
        Sprite.ICON_INTEL = getImageResource("/sprites/icons/intel.png");
        Sprite.ICON_LIFESTEAL = getImageResource("/sprites/icons/lifesteal.png");
        Sprite.ICON_REGEN = getImageResource("/sprites/icons/regen.png");
        Sprite.ICON_GRENADE = getImageResource("/sprites/icons/grenade.png");
        Sprite.ICON_SCAN = getImageResource("/sprites/icons/scan.png");
        Sprite.ICON_SHIELD = getImageResource("/sprites/icons/shield.png");
        progress++;

        status = "Loading game object sprites";
        SkinPopulator.populate();
        TilePopulator.populateTileSprites();
        GunPopulator.populateSprites();
        progress++;

        status = "Loading game screens";
        screens.put(GameState.MAIN_MENU, new MainMenuScreen(screenManager, game, 0, 0));
        screens.put(GameState.CHARACTER, new CharacterScreen(screenManager, game, 0, 0));
        screens.put(GameState.SETTINGS, new SettingScreen(screenManager, game, 0, 0));
        progress++;

        status = "Loading game information";
        Character.getInstance();
        progress++;

        state++;
    }

    @Override
    public void tick() {
        long current = System.nanoTime();
        double tickRatio = 1e9 / 255.0;
        switch (state) {
            case 0:
                delta += (current - previous) / tickRatio;
                previous = current;
                while (delta >= 1) {
                    if (alpha > 0) {
                        alpha--;
                    } else {
                        thread.start();
                        state++;
                        delta = 0;
                        break;
                    }
                    faderColor = new Color(0, 0, 0, alpha);
                    delta--;
                }
                break;
            case 2:
                delta += (current - previous) / tickRatio;
                previous = current;
                while (delta >= 1) {
                    if (alpha < 255) {
                        alpha++;
                    } else {
                        screenManager.setGameState(GameState.MAIN_MENU);
                        state++;
                        break;
                    }
                    faderColor = new Color(0, 0, 0, alpha);
                    delta--;
                }
                break;
        }
    }

    @Override
    public void render(Graphics2D graphics) {
        Color prevColor = graphics.getColor();
        Font prevFont = graphics.getFont();

        // Background
        graphics.setColor(Color.DARK_GRAY);
        graphics.fillRect(0, 0, width, height);

        // Logo
        int logoStartX = width / 2 - logo.getWidth() / 2, logoStartY = height / 2 - logo.getHeight() / 2 - 100;
        graphics.drawImage(logo, logoStartX, logoStartY, 400, 250, null);

        // Progress bar
        int barStartX = width / 2 - outline.getWidth() / 2, barStartY = height - 200, midWidth = (int) (progress * 990 / 5f);
        if (midWidth == 0) midWidth = 5;
        graphics.drawImage(outline, barStartX, barStartY, 1000, 15, null);
        graphics.drawImage(leftFill, barStartX, barStartY, 5, 15, null);
        graphics.drawImage(midFill, barStartX + 5, barStartY, midWidth, 15, null);
        graphics.drawImage(rightFill, barStartX + 5 + midWidth, barStartY, 5, 15, null);

        // Loading status
        Rectangle2D textBounds = graphics.getFontMetrics(statusFont).getStringBounds(status, graphics);
        int statusStartX = (int) (width / 2 - textBounds.getCenterX()), statusStartY = barStartY + 50;
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.setFont(statusFont);
        graphics.drawString(status, statusStartX, statusStartY);

        // Fader Overlay
        graphics.setColor(faderColor);
        graphics.fillRect(0, 0, width, height);

        graphics.setColor(prevColor);
        graphics.setFont(prevFont);
    }

    @Override
    public void refresh() {

    }

    @Override
    public void onComponentClick(String name) {

    }

}
