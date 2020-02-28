package me.thatonerr.slayone.projectiles;

import me.thatonerr.slayone.Game;
import me.thatonerr.slayone.entities.Entity;
import me.thatonerr.slayone.framework.FOV;
import me.thatonerr.slayone.framework.Hitbox;

import java.awt.*;

public abstract class Projectile extends Entity {

    protected float angle;

    public Projectile(float angle, Hitbox hitbox, Hitbox tileHitbox, Game game, float xStart, float yStart, int width, int height) {
        super(hitbox, tileHitbox, game, xStart, yStart, width, height);
        this.angle = angle;
    }

    public abstract void renderForPlayer(Graphics2D graphics, FOV fov);

    @Override
    public void render(Graphics2D graphics) {

    }

}
