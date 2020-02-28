package me.thatonerr.slayone.listeners;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.HashMap;
import java.util.Map;

public class MouseInput implements MouseInputListener, MouseWheelListener {

    private int mouseX, mouseY, scrolls;
    private Map<Integer, Boolean> activeKeys = new HashMap<>();
    private int active = 0;
    private boolean pressed;

    @Override
    public void mouseMoved(MouseEvent event) {
        mouseX = event.getX();
        mouseY = event.getY();
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        mouseX = event.getX();
        mouseY = event.getY();
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        mouseX = event.getX();
        mouseY = event.getY();
    }

    @Override
    public void mousePressed(MouseEvent event) {
        active = event.getButton();
        activeKeys.put(event.getButton(), true);
        pressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        active = -1;
        activeKeys.remove(event.getButton());
        pressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent event) {

    }

    @Override
    public void mouseExited(MouseEvent event) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent event) {
        scrolls = event.getWheelRotation();
    }

    public int getMouseX() {
        return mouseX;
    }

    public void setMouseX(int mouseX) {
        this.mouseX = mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public void setMouseY(int mouseY) {
        this.mouseY = mouseY;
    }

    public boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }

    public int getScrolls() {
        return scrolls;
    }

    public void setScrolls(int scrolls) {
        this.scrolls = scrolls;
    }

    public Map<Integer, Boolean> getActiveKeys() {
        return activeKeys;
    }

    public int getActive() {
        return active;
    }

}
