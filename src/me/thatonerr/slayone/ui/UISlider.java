package me.thatonerr.slayone.ui;

import me.thatonerr.slayone.Game;
import me.thatonerr.slayone.assets.GameFont;
import me.thatonerr.slayone.assets.Sprite;
import me.thatonerr.slayone.values.UIState;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class UISlider extends UIResponsive {

    private int percentage = 100;
    private Color textColor;
    private Font textFont = GameFont.SETTINGS_SUBCONTENT;
    private BufferedImage outline = Sprite.SETTINGS_SLIDER_OUTLINE;
    private BufferedImage leftFill = Sprite.SETTINGS_SLIDER_LEFT_FILL;
    private BufferedImage midFill = Sprite.SETTINGS_SLIDER_MID_FILL;
    private BufferedImage thumbIdle = Sprite.SETTINGS_SLIDER_THUMB_IDLE;
    private BufferedImage thumbHover = Sprite.SETTINGS_SLIDER_THUMB_HOVER;
    private BufferedImage thumbActive = Sprite.SETTINGS_SLIDER_THUMB_ACTIVE;

    public UISlider(String name, Color textColor, UIScreen screen, Game game, float x, float y) {
        super(name, screen, game, x, y, 600, 25);
        this.textColor = textColor;
    }

    @Override
    public void tick() {
        int mouseX = mouse.getMouseX(), mouseY = mouse.getMouseY();
        if (pressed && state == UIState.DRAG) {
            if (mouse.isPressed()) {
                // 100% = xStart + width / 2, xStart = 0%, xEnd = 200%;
                percentage = (int) ((mouseX - xStart) * 200 / width);
                if (percentage > 200) percentage = 200;
                if (percentage < 0) percentage = 0;
                return;
            } else {
                pressed = false;
            }
        }
        if (!(mouseX >= xStart && mouseX <= xEnd && mouseY >= yStart && mouseY <= yEnd)) {
            state = UIState.IDLE;
            pressed = false;
            return;
        }
        onHover();
        if (mouse.isPressed()) {
            state = UIState.DRAG;
            pressed = true;
        } else if (pressed) {
            pressed = false;
            onClick();
        }
    }



    @Override
    public void render(Graphics2D graphics) {
        // Slider image
        int fillWidth = percentage * 3, trackStartY = (int) yStart + 5;
        graphics.drawImage(outline, (int) xStart, trackStartY, width, 15, null);
        graphics.drawImage(leftFill, (int) xStart, trackStartY, 5, 15, null);
        graphics.drawImage(midFill, (int) xStart + 5, trackStartY, fillWidth, 15, null);
        switch (state) {
            case IDLE:
                graphics.drawImage(thumbIdle, (int) xStart + fillWidth - 12, (int) yStart, 25, 25, null);
                break;
            case HOVER:
                graphics.drawImage(thumbHover, (int) xStart + fillWidth - 12, (int) yStart, 25, 25, null);
                break;
            case CLICK:
            case DRAG:
            case ACTIVE:
                graphics.drawImage(thumbActive, (int) xStart + fillWidth - 12, (int) yStart, 25, 25, null);
                break;
        }

        // Percentage Text
        String text = percentage + "%";
        Rectangle2D textBounds = graphics.getFontMetrics(textFont).getStringBounds(text, graphics);
        int textStartX = (int) (xEnd + 50 - textBounds.getCenterX()), textStartY = (int) (yStart + height / 2 - textBounds.getCenterY());
        graphics.setFont(textFont);
        graphics.setColor(textColor);
        graphics.drawString(text, textStartX, textStartY);
    }

}
