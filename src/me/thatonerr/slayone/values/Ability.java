package me.thatonerr.slayone.values;

public enum Ability {

    GRENADE("Frag Grenade"),
    SCAN("Scan"),
    SHIELD("Energy Shield");

    private String name;

    Ability(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
