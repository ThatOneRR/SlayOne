package me.thatonerr.slayone.abilities;

public class ScanAbility extends AbilityInfo {

    private int duration = 0;

    @Override
    public void resetAllPoints() {
        duration = 0;
    }

    @Override
    public int getAllPoints() {
        return 1 + duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

}
