package me.thatonerr.slayone.framework;

public class FOV {

    private int xOffset, yOffset, mouseXOffset, mouseYOffset, totalXOffset, totalYOffset, xStart, xEnd, yStart, yEnd;

    public FOV(int xOffset, int yOffset, int mouseXOffset, int mouseYOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.mouseXOffset = mouseXOffset;
        this.mouseYOffset = mouseYOffset;
        xStart = 0;
        xEnd = 0;
        yStart = 0;
        yEnd = 0;
    }

    public int getxOffset() {
        return xOffset;
    }

    public void setxOffset(int xOffset) {
        this.xOffset = xOffset;
    }

    public int getyOffset() {
        return yOffset;
    }

    public void setyOffset(int yOffset) {
        this.yOffset = yOffset;
    }

    public int getMouseXOffset() {
        return mouseXOffset;
    }

    public void setMouseXOffset(int mouseXOffset) {
        this.mouseXOffset = mouseXOffset;
    }

    public int getMouseYOffset() {
        return mouseYOffset;
    }

    public void setMouseYOffset(int mouseYOffset) {
        this.mouseYOffset = mouseYOffset;
    }

    public int getTotalXOffset() {
        return totalXOffset;
    }

    public void setTotalXOffset(int totalXOffset) {
        this.totalXOffset = totalXOffset;
    }

    public int getTotalYOffset() {
        return totalYOffset;
    }

    public void setTotalYOffset(int totalYOffset) {
        this.totalYOffset = totalYOffset;
    }

    public int getxStart() {
        return xStart;
    }

    public void setxStart(int xStart) {
        this.xStart = xStart;
    }

    public int getxEnd() {
        return xEnd;
    }

    public void setxEnd(int xEnd) {
        this.xEnd = xEnd;
    }

    public int getyStart() {
        return yStart;
    }

    public void setyStart(int yStart) {
        this.yStart = yStart;
    }

    public int getyEnd() {
        return yEnd;
    }

    public void setyEnd(int yEnd) {
        this.yEnd = yEnd;
    }

}
