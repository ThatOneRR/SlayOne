package me.thatonerr.slayone.projectiles;

import me.thatonerr.slayone.Game;
import me.thatonerr.slayone.blocks.Block;
import me.thatonerr.slayone.entities.Entity;
import me.thatonerr.slayone.entities.LivingEntity;
import me.thatonerr.slayone.framework.FOV;
import me.thatonerr.slayone.framework.GameMap;
import me.thatonerr.slayone.framework.Hitbox;
import me.thatonerr.slayone.utilities.Utils;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

public class LaserBullet extends Projectile {

    private boolean isDead = false;
    private static BufferedImage down;
    private BufferedImage newSprite;
    private Map<Integer, Map<Integer, Block>> blockMap;
    private List<Entity> entityMap;
    private int bufferTime = 0;

    public LaserBullet(GameMap gameMap, float angle, Game game, float xStart, float yStart) {
        super(angle, null, null, game, xStart - 15, yStart - 15, 30, 30);
        blockMap = gameMap.getBlockMap();
        entityMap = gameMap.getEntities();
        angle -= 270;
        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(angle), down.getWidth() / 2, down.getHeight() / 2);
        AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
        newSprite = op.filter(down, null);
        if (angle < 0) angle += 360;
        double angleRad = Math.toRadians(angle);
        float bulletSpeed = 10;
        if (angle >= 0 && angle < 90) {
            velX = (float) (bulletSpeed * Math.sin(angleRad));
            velY = (float) (bulletSpeed * Math.cos(angleRad));
        } else if (angle >= 90 && angle < 180) {
            velY = (float) (bulletSpeed * Math.sin(angleRad - Math.PI / 2));
            velX = (float) (bulletSpeed * Math.cos(angleRad - Math.PI / 2));
        } else if (angle >= 180 && angle < 270) {
            velX = (float) (bulletSpeed * Math.sin(angleRad - Math.PI));
            velY = (float) (bulletSpeed * Math.cos(angleRad - Math.PI));
        } else if (angle >= 270 && angle < 360) {
            velY = (float) (bulletSpeed * Math.sin(angleRad - Math.PI * 1.5));
            velX = (float) (bulletSpeed * Math.cos(angleRad - Math.PI * 1.5));
        }
        if (angle >= 0 && angle < 180) velX *= -1;
        if (angle >= 90 && angle < 270) velY *= -1;
    }

    public static void getSprites() {
        String name = "laser_bullet";
        down = Utils.getImageResource("/sprites/projectiles/" + name + "/down.png");
    }

    @Override
    public int getMovability(float incVelX, float incVelY) {
        return 0;
    }

    @Override
    public void tick() {
        if (bufferTime < 10) bufferTime++;
        if (isDead) return;
        yStart += velY;
        checkCollisionY();
        xStart += velX;
        checkCollisionX();
        updatePosition();
        checkHit();
    }

    private void checkHit() {
        if (bufferTime < 10) return;
        for (Entity entity : entityMap) {
            if (!(entity instanceof LivingEntity)) continue;
            LivingEntity livingEntity = (LivingEntity) entity;
            Hitbox hitbox = livingEntity.getHitbox();
            float startBoundX = livingEntity.getXStart() + hitbox.getX(), startBoundY = livingEntity.getYStart() + hitbox.getY(), endBoundX = startBoundX + hitbox.getWidth(), endBoundY = startBoundY + hitbox.getHeight();
            if (xStart >= startBoundX && xStart <= endBoundX && yStart >= startBoundY && yStart <= endBoundY || xEnd >= startBoundX && xEnd <= endBoundX && yEnd >= startBoundY && yEnd <= endBoundY) {
                isDead = true;
                break;
            }
        }
    }

    private void checkCollisionX() {
        if (velX > 0) {
            // Moving right
            Block topBlock = blockMap.get((int) (yStart) / 60).get((int) (xStart + 29) / 60);
            Block bottomBlock = blockMap.get((int) (yStart + 29) / 60).get((int) (xStart + 29) / 60);
            if (topBlock != null) {
                isDead = true;
            }
            if (bottomBlock != null) {
                isDead = true;
            }
        } else if (velX < 0) {
            // Moving left
            Block topBlock = blockMap.get((int) (yStart) / 60).get((int) (xStart) / 60);
            Block bottomBlock = blockMap.get((int) (yStart + 29) / 60).get((int) (xStart) / 60);
            if (topBlock != null) {
                isDead = true;
            }
            if (bottomBlock != null) {
                isDead = true;
            }
        }
    }

    private void checkCollisionY() {
        if (velY > 0) {
            // Going down
            Block leftBlock = blockMap.get((int) (yStart + 29) / 60).get((int) (xStart) / 60);
            Block rightBlock = blockMap.get((int) (yStart + 29) / 60).get((int) (xStart + 29) / 60);
            if (leftBlock != null) {
                isDead = true;
            }
            if (rightBlock != null) {
                isDead = true;
            }
        } else if (velY < 0) {
            // Going up
            Block leftBlock = blockMap.get((int) (yStart) / 60).get((int) (xStart) / 60);
            Block rightBlock = blockMap.get((int) (yStart) / 60).get((int) (xStart + 29) / 60);
            if (leftBlock != null) {
                isDead = true;
            }
            if (rightBlock != null) {
                isDead = true;
            }
        }
    }

    @Override
    public void renderForPlayer(Graphics2D graphics, FOV fov) {
        if (isDead) return;
        // Rendering efficiency (don't render tiles if they're outside the screen)
        if (xEnd < fov.getxStart() || xStart > fov.getxEnd() || yEnd < fov.getyStart() || yStart > fov.getyEnd()) return;

        graphics.drawImage(newSprite, (int) xStart + fov.getTotalXOffset(), (int) yStart + fov.getTotalYOffset() - 20, 30, 30, null);
        graphics.setColor(new Color(188, 250, 255, 39));
        graphics.fillOval((int) xStart - 15 + fov.getTotalXOffset(), (int) yStart - 15 + fov.getTotalYOffset() - 20, 60, 60);
        graphics.fillOval((int) xStart - 10 + fov.getTotalXOffset(), (int) yStart - 10 + fov.getTotalYOffset() - 20, 50, 50);
        graphics.fillOval((int) xStart - 5 + fov.getTotalXOffset(), (int) yStart - 5 + fov.getTotalYOffset() - 20, 40, 40);
    }

}
