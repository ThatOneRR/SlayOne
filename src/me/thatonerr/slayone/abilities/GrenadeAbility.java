package me.thatonerr.slayone.abilities;

import me.thatonerr.slayone.values.Ability;

public class GrenadeAbility extends AbilityInfo {

    private Ability ability = Ability.GRENADE;
    private int range, damage;

    @Override
    public void resetAllPoints() {
        range = 0;
        damage = 0;
    }

    @Override
    public int getAllPoints() {
        return 1 + range + damage;
    }

    public Ability getAbility() {
        return ability;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

}
