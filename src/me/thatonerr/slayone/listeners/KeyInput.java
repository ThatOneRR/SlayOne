package me.thatonerr.slayone.listeners;

import java.util.HashMap;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

public class KeyInput implements KeyListener {

    private boolean[] keys = new boolean[500];
    private int activeKey = -1;
    private Map<Integer, Boolean> activeKeys = new HashMap<>();

    @Override
    public void keyPressed(KeyEvent event) {
        activeKey = event.getKeyCode();
        activeKeys.put(event.getKeyCode(), true);
        keys[activeKey] = true;
    }

    @Override
    public void keyReleased(KeyEvent event) {
        activeKey = -1;
        activeKeys.remove(event.getKeyCode());
        keys[event.getKeyCode()] = false;
    }

    @Override
    public void keyTyped(KeyEvent event) {

    }

    public int getActiveKey() {
        return activeKey;
    }

    public Map<Integer, Boolean> getActiveKeys() {
        return activeKeys;
    }

}
