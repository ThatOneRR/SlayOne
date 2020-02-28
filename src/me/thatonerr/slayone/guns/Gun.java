package me.thatonerr.slayone.guns;

import me.thatonerr.slayone.Game;
import me.thatonerr.slayone.framework.FOV;
import me.thatonerr.slayone.framework.GameMap;
import me.thatonerr.slayone.values.GunType;

import java.awt.*;

public abstract class Gun {

    protected int ammo, clipAmmo, maxAmmo, direction, recoilSpeed, recoilSpeedMax, recoilUndoSpeed, reload, reloadSpeed;
    protected GunType gunType;

    public Gun(int ammo, int clipAmmo, int maxAmmo, int direction, int recoilSpeed, int recoilSpeedMax, int recoilUndoSpeed, int reload, int reloadSpeed, GunType gunType) {
        this.ammo = ammo;
        this.clipAmmo = clipAmmo;
        this.maxAmmo = maxAmmo;
        this.direction = direction;
        this.recoilSpeed = recoilSpeed;
        this.recoilSpeedMax = recoilSpeedMax;
        this.recoilUndoSpeed = recoilUndoSpeed;
        this.reload = reload;
        this.reloadSpeed = reloadSpeed;
        this.gunType = gunType;
    }

    public abstract void shoot(GameMap gameMap, Game game, float angle, int xStart, int yStart);
    public abstract void tick();

    public abstract void render(Graphics2D graphics, FOV fov, int xStart, int yStart, int displaceX, int displaceY);
    public abstract void renderHUD(Graphics2D graphics, FOV fov, int xStart, int yStart);

    public int getRecoilSpeedMax() {
        return recoilSpeedMax;
    }

    public void setRecoilSpeedMax(int recoilSpeedMax) {
        this.recoilSpeedMax = recoilSpeedMax;
    }

    public int getRecoilSpeed() {
        return recoilSpeed;
    }

    public void setRecoilSpeed(int recoilSpeed) {
        this.recoilSpeed = recoilSpeed;
    }

    public int getRecoilUndoSpeed() {
        return recoilUndoSpeed;
    }

    public void setRecoilUndoSpeed(int recoilUndoSpeed) {
        this.recoilUndoSpeed = recoilUndoSpeed;
    }

    public int getReload() {
        return reload;
    }

    public void setReload(int reload) {
        this.reload = reload;
    }

    public int getReloadSpeed() {
        return reloadSpeed;
    }

    public void setReloadSpeed(int reloadSpeed) {
        this.reloadSpeed = reloadSpeed;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public int getClipAmmo() {
        return clipAmmo;
    }

    public void setClipAmmo(int clipAmmo) {
        this.clipAmmo = clipAmmo;
    }

    public int getMaxAmmo() {
        return maxAmmo;
    }

    public void setMaxAmmo(int maxAmmo) {
        this.maxAmmo = maxAmmo;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public GunType getGunType() {
        return gunType;
    }

    public void setGunType(GunType gunType) {
        this.gunType = gunType;
    }

}
