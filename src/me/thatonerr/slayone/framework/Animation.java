package me.thatonerr.slayone.framework;

import java.awt.image.BufferedImage;
import java.util.List;

public class Animation {

    private int index = 0, tps, tick = 0;
    private List<BufferedImage> frames;

    public Animation(int tps, List<BufferedImage> frames) {
        this.tps = tps;
        this.frames = frames;
    }

    public void tick() {
        tick++;
        if (tick > tps) {
            tick = 0;
            index++;
        }
        if (index >= frames.size()) {
            index = 0;
        }
    }

    public BufferedImage getCurrentFrame() {
        return frames.get(index);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}
