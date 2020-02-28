package me.thatonerr.slayone.values;

public enum GunType {

    LASER_PISTOL("Laser Pistol");

    private String name;

    GunType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
