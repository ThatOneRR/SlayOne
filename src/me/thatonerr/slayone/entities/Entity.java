package me.thatonerr.slayone.entities;

import me.thatonerr.slayone.Game;
import me.thatonerr.slayone.framework.GameObject;
import me.thatonerr.slayone.framework.Hitbox;

public abstract class Entity extends GameObject {

    protected Hitbox hitbox, tileHitbox;
    protected float velX = 0, velY = 0;

    public Entity(Hitbox hitbox, Hitbox tileHitbox, Game game, float xStart, float yStart, int width, int height) {
        super(game, xStart, yStart, width, height);
        this.hitbox = hitbox;
        this.tileHitbox = tileHitbox;
    }

    public abstract int getMovability(float incVelX, float incVelY);

    public Hitbox getHitbox() {
        return hitbox;
    }

    public Hitbox getTileHitbox() {
        return tileHitbox;
    }

}
