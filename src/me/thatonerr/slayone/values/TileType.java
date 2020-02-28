package me.thatonerr.slayone.values;

public enum TileType {

    ALLOY_1(1, "Alloy 1"),
    BOUNDARY(2, "Boundary");

    private int tileID;
    private String name;

    TileType(int tileID, String name) {
        this.tileID = tileID;
        this.name = name;
    }

    public int getTileID() {
        return tileID;
    }

    public String getName() {
        return name;
    }

}
