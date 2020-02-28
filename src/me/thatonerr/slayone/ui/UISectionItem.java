package me.thatonerr.slayone.ui;

import me.thatonerr.slayone.Game;
import me.thatonerr.slayone.values.UIState;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class UISectionItem extends UIResponsive {

    private String text;
    private Font textFont;
    private Color textColor, hoverTextColor, activeTextColor, bgColor, hoverBgColor, activeBgColor;

    public UISectionItem(String text, Font textFont, Color textColor, Color hoverTextColor, Color activeTextColor, Color bgColor, Color hoverBgColor, Color activeBgColor, UIScreen screen, Game game, float x, float y, int width, int height) {
        super(text, screen, game, x, y, width, height);
        this.text = text;
        this.textFont = textFont;
        this.textColor = textColor;
        this.hoverTextColor = hoverTextColor;
        this.activeTextColor = activeTextColor;
        this.bgColor = bgColor;
        this.hoverBgColor = hoverBgColor;
        this.activeBgColor = activeBgColor;
    }

    @Override
    public void tick() {
        if (state == UIState.ACTIVE) {
            return;
        }
        int mouseX = mouse.getMouseX(), mouseY = mouse.getMouseY();
        if (!(mouseX >= xStart && mouseX <= xEnd && mouseY >= yStart && mouseY <= yEnd)) {
            state = UIState.IDLE;
            pressed = false;
            return;
        }
        onHover();
        if (mouse.isPressed()) {
            pressed = true;
        } else if (pressed) {
            pressed = false;
            onClick();
        }
    }

    @Override
    public void onClick() {
        super.onClick();
        state = UIState.ACTIVE;
    }

    @Override
    public void render(Graphics2D graphics) {
        Color prevColor = graphics.getColor();
        Font prevFont = graphics.getFont();

        // Background
        if (state == UIState.HOVER && hoverBgColor != null) {
            graphics.setColor(hoverBgColor);
            graphics.fillRect((int) xStart, (int) yStart, width, height);
        } else if (bgColor != null && state == UIState.IDLE) {
            graphics.setColor(bgColor);
            graphics.fillRect((int) xStart, (int) yStart, width, height);
        } else if (activeBgColor != null && state == UIState.ACTIVE) {
            graphics.setColor(activeBgColor);
            graphics.fillRect((int) xStart, (int) yStart, width, height);
        }

        // Drawing text
        Rectangle2D textBounds = graphics.getFontMetrics(textFont).getStringBounds(text, graphics);
        int textStartX = (int) (xStart + width / 2 - textBounds.getCenterX()), textStartY = (int) (yStart + height / 2 - textBounds.getCenterY());
        graphics.setFont(textFont);
        graphics.setColor(textColor);
        if (state == UIState.HOVER) {
            graphics.setColor(hoverTextColor);
        } else if (state == UIState.ACTIVE) {
            graphics.setColor(activeTextColor);
        }
        graphics.drawString(text, textStartX, textStartY);

        graphics.setColor(prevColor);
        graphics.setFont(prevFont);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
