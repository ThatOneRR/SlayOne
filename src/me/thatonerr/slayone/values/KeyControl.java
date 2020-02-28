package me.thatonerr.slayone.values;

import static java.awt.event.KeyEvent.*;
import static java.awt.event.MouseEvent.*;

public enum KeyControl {

    SHOOT(BUTTON1),
    MOVE_UP(VK_W),
    MOVE_LEFT(VK_A),
    MOVE_DOWN(VK_S),
    MOVE_RIGHT(VK_D),
    JUMP(VK_W),
    RELOAD(VK_R),
    SCOPE(VK_F),
    AB_1(VK_Q),
    AB_2(VK_E);

    private int keyCode;

    KeyControl(int keyCode) {
        this.keyCode = keyCode;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    public static KeyControl getKeyByName(String name) {
        switch (name.toLowerCase()) {
            case "shoot":
                return SHOOT;
            case "up":
                return MOVE_UP;
            case "left":
                return MOVE_LEFT;
            case "down":
                return MOVE_DOWN;
            case "right":
                return MOVE_RIGHT;
            case "jump":
                return JUMP;
            case "reload":
                return RELOAD;
            case "scope":
                return SCOPE;
            case "ab1":
                return AB_1;
            case "ab2":
                return AB_2;
            default:
                return null;
        }
    }

}
