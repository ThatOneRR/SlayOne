package me.thatonerr.slayone.info;

public class Passive {

    private int agility, health, intel, lifesteal, regen;

    public Passive(int agility, int health, int intel, int lifesteal, int regen) {
        this.agility = agility;
        this.health = health;
        this.intel = intel;
        this.lifesteal = lifesteal;
        this.regen = regen;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getIntel() {
        return intel;
    }

    public void setIntel(int intel) {
        this.intel = intel;
    }

    public int getLifesteal() {
        return lifesteal;
    }

    public void setLifesteal(int lifesteal) {
        this.lifesteal = lifesteal;
    }

    public int getRegen() {
        return regen;
    }

    public void setRegen(int regen) {
        this.regen = regen;
    }

}
