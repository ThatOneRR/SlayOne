package me.thatonerr.slayone.guns;

import me.thatonerr.slayone.Game;
import me.thatonerr.slayone.framework.FOV;
import me.thatonerr.slayone.framework.GameMap;
import me.thatonerr.slayone.projectiles.LaserBullet;
import me.thatonerr.slayone.values.GunType;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static me.thatonerr.slayone.utilities.Utils.getImageResource;

public class LaserPistol extends Gun {

    private static List<BufferedImage> sprites = new ArrayList<>();

    public LaserPistol() {
        super(-1, -1, -1, 0, 2, 3, 1, 60, 60, GunType.LASER_PISTOL);
    }

    public static void getSprites() {
        String name = "laser_pistol";
        sprites.add(getImageResource("/sprites/guns/" + name + "/down.png"));
        sprites.add(getImageResource("/sprites/guns/" + name + "/down_left.png"));
        sprites.add(getImageResource("/sprites/guns/" + name + "/left.png"));
        sprites.add(getImageResource("/sprites/guns/" + name + "/up_left.png"));
        sprites.add(getImageResource("/sprites/guns/" + name + "/up_right.png"));
        sprites.add(getImageResource("/sprites/guns/" + name + "/up_right.png"));
        sprites.add(getImageResource("/sprites/guns/" + name + "/right.png"));
        sprites.add(getImageResource("/sprites/guns/" + name + "/down_right.png"));
    }

    @Override
    public void shoot(GameMap gameMap, Game game, float angle, int xStart, int yStart) {
        reload = 0;
        switch (direction) {
            case 0:
                xStart += 9;
                yStart += 36;
                break;
            case 1:
                xStart += 15;
                yStart += 42;
                break;
            case 2:
                xStart -= 36;
                yStart += 10;
                break;
            case 3:
                xStart -= 36;
                yStart -= 21;
                break;
            case 5:
                xStart += 33;
                break;
            case 6:
                xStart += 36;
                yStart += 21;
                break;
            case 7:
                xStart += 45;
                yStart += 44;
                break;
        }
        gameMap.addEntity(new LaserBullet(gameMap, angle, game, xStart, yStart));
    }

    @Override
    public void renderHUD(Graphics2D graphics, FOV fov, int xStart, int yStart) {
        if (reloadSpeed == reload) return;
        graphics.setColor(new Color(180, 180, 180));
        graphics.fillRect(xStart - 52 + fov.getTotalXOffset(), yStart - 42 + fov.getTotalYOffset(), 104, 24);
        graphics.setColor(new Color(100, 100, 100));
        graphics.fillRect(xStart - 50 + fov.getTotalXOffset(), yStart - 40 + fov.getTotalYOffset(), 100, 20);
        graphics.setColor(new Color(113, 174, 213));
        graphics.fillRect(xStart - 50 + fov.getTotalXOffset(), yStart - 40 + fov.getTotalYOffset(), (reloadSpeed - reload) * 100 / reloadSpeed, 20);
    }

    @Override
    public void tick() {
        if (reload < reloadSpeed) reload++;
    }

    @Override
    public void render(Graphics2D graphics, FOV fov, int xStart, int yStart, int displaceX, int displaceY) {
        int width = 0, height = 0;
        switch (direction) {
            case 0:
                width = 18;
                height = 36;
                break;
            case 1:
            case 7:
                width = 48;
                height = 42;
                break;
            case 2:
            case 6:
                width = 48;
                height = 24;
                break;
            case 3:
            case 5:
                width = 36;
                height = 42;
                break;
            case 4:
                return;
        }
        if (displaceX == 1) {
            xStart -= width;
        } else if (displaceX == 2) {
            xStart += width;
        }
        if (displaceY == 1) {
            yStart -= height;
        } else if (displaceY == 2) {
            yStart += height;
        }
        graphics.drawImage(sprites.get(direction), xStart, yStart, width, height, null);
    }

}
