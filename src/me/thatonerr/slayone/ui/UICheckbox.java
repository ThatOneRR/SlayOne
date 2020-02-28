package me.thatonerr.slayone.ui;

import me.thatonerr.slayone.Game;
import me.thatonerr.slayone.assets.Sprite;
import me.thatonerr.slayone.values.UIState;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UICheckbox extends UIResponsive {

    private boolean disabled, active;
    private BufferedImage empty = Sprite.CHECKBOX_EMPTY, emptyHover = Sprite.CHECKBOX_EMPTY_HOVER, filled = Sprite.CHECKBOX_FILLED, filledHover = Sprite.CHECKBOX_FILLED_HOVER;

    public UICheckbox(String name, UIScreen screen, Game game, float x, float y, int width, int height) {
        super(name, screen, game, x, y, width, height);
    }

    @Override
    public void onClick() {
        super.onClick();
    }

    @Override
    public void tick() {
        if (disabled) {
            state = UIState.IDLE;
            return;
        }
        super.tick();
    }

    @Override
    public void render(Graphics2D graphics) {
        if (active) {
            switch (state) {
                case IDLE:
                    graphics.drawImage(filled, (int) xStart, (int) yStart, width, height, null);
                    break;
                case HOVER:
                    graphics.drawImage(filledHover, (int) xStart, (int) yStart, width, height, null);
                    break;
            }
        } else {
            switch (state) {
                case IDLE:
                    graphics.drawImage(empty, (int) xStart, (int) yStart, width, height, null);
                    break;
                case HOVER:
                    graphics.drawImage(emptyHover, (int) xStart, (int) yStart, width, height, null);
                    break;
            }
        }

        if (disabled) {
            graphics.setColor(new Color(100, 100, 100, 150));
            graphics.fillRect((int) xStart, (int) yStart, width, height);
        }
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
