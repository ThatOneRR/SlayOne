package me.thatonerr.slayone.ui;

import me.thatonerr.slayone.Game;
import me.thatonerr.slayone.values.UIState;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class UIButton extends UIResponsive {

    private boolean disabled;
    private String text;
    private Font textFont;
    private Color textColor, hoverTextColor, bgColor, hoverBgColor;

    public UIButton(String text, Font textFont, Color textColor, Color hoverTextColor, Color bgColor, Color hoverBgColor, UIScreen screen, Game game, float x, float y, int width, int height) {
        super(text, screen, game, x, y, width, height);
        this.text = text;
        this.textFont = textFont;
        this.textColor = textColor;
        this.hoverTextColor = hoverTextColor;
        this.bgColor = bgColor;
        this.hoverBgColor = hoverBgColor;
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
        Color prevColor = graphics.getColor();
        Font prevFont = graphics.getFont();

        // Background
        if (state == UIState.HOVER && hoverBgColor != null) {
            graphics.setColor(hoverBgColor);
            graphics.fillRect((int) xStart, (int) yStart, width, height);
        } else if (bgColor != null && state == UIState.IDLE) {
            graphics.setColor(bgColor);
            graphics.fillRect((int) xStart, (int) yStart, width, height);
        }

        // Drawing text
        Rectangle2D textBounds = graphics.getFontMetrics(textFont).getStringBounds(text, graphics);
        int textStartX = (int) (xStart + width / 2 - textBounds.getCenterX()), textStartY = (int) (yStart + height / 2 - textBounds.getCenterY());
        graphics.setFont(textFont);
        graphics.setColor(textColor);
        if (state == UIState.HOVER) {
            graphics.setColor(hoverTextColor);
        }
        graphics.drawString(text, textStartX, textStartY);

        if (disabled) {
            graphics.setColor(new Color(100, 100, 100, 150));
            graphics.fillRect((int) xStart, (int) yStart, width, height);
        }

        graphics.setColor(prevColor);
        graphics.setFont(prevFont);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

}
