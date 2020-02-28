package me.thatonerr.slayone.info;

import me.thatonerr.slayone.abilities.AbilityInfo;

public class Character {

    private static Character instance;
    private int points;
    private Skin skin;
    private Passive passive;
    private AbilityInfo ability1, ability2;

    private Character() {
        points = 12;
        passive = new Passive(0, 0, 0, 0, 0);
        skin = Skin.skins.get("Default");
    }

    public Skin getSkin() {
        return skin;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public AbilityInfo getAbility1() {
        return ability1;
    }

    public void setAbility1(AbilityInfo ability1) {
        this.ability1 = ability1;
    }

    public AbilityInfo getAbility2() {
        return ability2;
    }

    public void setAbility2(AbilityInfo ability2) {
        this.ability2 = ability2;
    }

    public Passive getPassive() {
        return passive;
    }

    public static Character getInstance() {
        if (instance == null) instance = new Character();
        return instance;
    }

}
