package me.thatonerr.slayone.ui;

import me.thatonerr.slayone.Game;
import me.thatonerr.slayone.framework.GameObject;
import me.thatonerr.slayone.listeners.MouseInput;
import me.thatonerr.slayone.values.UIState;

public abstract class UIResponsive extends GameObject {

    protected String name;
    protected UIScreen screen;
    protected boolean pressed;
    protected UIState state = UIState.IDLE;
    protected MouseInput mouse;

    public UIResponsive(String name, UIScreen screen, Game game, float x, float y, int width, int height) {
        super(game, x, y, width, height);
        this.name = name;
        this.screen = screen;
        mouse = game.getMouseInput();
    }

    @Override
    public void tick() {
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

    public void onHover() {
        state = UIState.HOVER;
    }

    public void onClick() {
        screen.onComponentClick(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UIState getState() {
        return state;
    }

    public void setState(UIState state) {
        this.state = state;
    }

}
