package me.thatonerr.slayone.ui;

import me.thatonerr.slayone.Game;
import me.thatonerr.slayone.assets.GameFont;
import me.thatonerr.slayone.listeners.KeyInput;
import me.thatonerr.slayone.listeners.MouseInput;
import me.thatonerr.slayone.utilities.Utils;
import me.thatonerr.slayone.values.KeyControl;
import me.thatonerr.slayone.values.UIState;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Map;

public class UIKeySwap extends UIResponsive {

    public static UIKeySwap current;
    private int page;
    private String keySF;
    private int keyCode;
    private String keyName;
    private Font textFont = GameFont.SETTINGS_SUBCONTENT;
    private Color keyBgIdle = new Color(140, 140, 140);
    private Color keyBgHover = new Color(120, 120, 120);
    private Color keyBgActive = new Color(90, 90, 90);
    private Color idleColor = Color.LIGHT_GRAY;
    private Color activeColor = Color.YELLOW;
    private Color hoverColor = Color.WHITE;
    private Map<String, UIKeySwap> keySwaps;

    private MouseInput mouseInput;
    private KeyInput keyInput;

    public UIKeySwap(String name, Map<String, UIKeySwap> keySwaps, int page, UIScreen screen, Game game, float x, float y, int width, int height) {
        super(name, screen, game, x, y, width, height);
        this.keyName = name;
        this.page = page;
        mouseInput = game.getMouseInput();
        keyInput = game.getKeyInput();
        this.keySwaps = keySwaps;
        refresh();
    }

    @Override
    public void tick() {
        if (current == this) {
            if (mouseInput.getActive() != -1) {
                int active = mouseInput.getActive();
                String text = Utils.getClickTextSF(active);
                if (active != -1) {
                    for (UIKeySwap each : keySwaps.values()) {
                        if (each == this) continue;
                        if (each.getKeyCode() == active) {
                            each.setKeyControl(-1, "None");
                        }
                    }
                }
                setKeyControl(active, text);
                current = null;
            } else if (keyInput.getActiveKey() != -1) {
                int active = keyInput.getActiveKey();
                String text = Utils.getKeyTextSF(active);
                if (active != -1) {
                    for (UIKeySwap each : keySwaps.values()) {
                        if (each == this) continue;
                        if (each.getKeyCode() == active) {
                            each.setKeyControl(-1, "None");
                        }
                    }
                }
                setKeyControl(active, text);
                current = null;
            }
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
        current = this;
        state = UIState.ACTIVE;
    }

    @Override
    public void render(Graphics2D graphics) {
        switch (state) {
            case IDLE:
                graphics.setColor(keyBgIdle);
                break;
            case HOVER:
                graphics.setColor(keyBgHover);
                break;
            case ACTIVE:
                graphics.setColor(keyBgActive);
                break;
        }
        graphics.fillRect((int) xStart, (int) yStart, width, height);

        Rectangle2D textBounds = graphics.getFontMetrics(textFont).getStringBounds(keySF, graphics);
        int textStartX = (int) (xStart + width / 2 - textBounds.getCenterX()), textStartY = (int) (yStart + height / 2 - textBounds.getCenterY());
        graphics.setFont(textFont);
        switch (state) {
            case IDLE:
                graphics.setColor(idleColor);
                break;
            case HOVER:
                graphics.setColor(hoverColor);
                break;
            case ACTIVE:
                graphics.setColor(activeColor);
                break;
        }
        graphics.drawString(keySF, textStartX, textStartY);
    }

    public void setKeyControl(int keyCode, String keySF) {
        this.keyCode = keyCode;
        this.keySF = keySF;
        if (keySF.equalsIgnoreCase("None")) {
            this.keyCode = -1;
        }
        KeyControl.getKeyByName(keyName).setKeyCode(keyCode);
    }

    public void refresh() {
        keyCode = KeyControl.getKeyByName(keyName).getKeyCode();
        keySF = Utils.getClickTextSF(keyCode);
        if (keySF.equalsIgnoreCase("None")) {
            keySF = Utils.getKeyTextSF(keyCode);
        }
    }

    public String getKeySF() {
        return keySF;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public String getKeyName() {
        return keyName;
    }

    public int getPage() {
        return page;
    }

}
