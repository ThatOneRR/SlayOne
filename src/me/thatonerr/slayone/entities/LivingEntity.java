package me.thatonerr.slayone.entities;

import me.thatonerr.slayone.Game;
import me.thatonerr.slayone.framework.Hitbox;

public abstract class LivingEntity extends Entity {

    protected int health, maxHealth, direction;

    public LivingEntity(int direction, int health, int maxHealth, Hitbox hitbox, Hitbox tileHitbox, Game game, float xStart, float yStart, int width, int height) {
        super(hitbox, tileHitbox, game, xStart, yStart, width, height);
        this.direction = direction;
        this.health = health;
        this.maxHealth = maxHealth;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

}
