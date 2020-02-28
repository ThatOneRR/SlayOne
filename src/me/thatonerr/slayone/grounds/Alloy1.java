package me.thatonerr.slayone.grounds;

import me.thatonerr.slayone.Game;
import me.thatonerr.slayone.framework.FOV;
import me.thatonerr.slayone.values.TileType;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static me.thatonerr.slayone.utilities.Utils.getImageResource;

public class Alloy1 extends Ground {

    private static final List<BufferedImage> sprites = new ArrayList<>();

    public Alloy1(Game game, int position, int tileX, int tileY) {
        super(TileType.ALLOY_1, game, position, tileX, tileY);
    }

    public static void getSprites() {
        String name = "alloy_1";
        sprites.add(getImageResource("/sprites/tiles/grounds/" + name + "/single.png"));
        sprites.add(getImageResource("/sprites/tiles/grounds/" + name + "/all_joint.png"));
        sprites.add(getImageResource("/sprites/tiles/grounds/" + name + "/bottom_joint.png"));
        sprites.add(getImageResource("/sprites/tiles/grounds/" + name + "/left_joint.png"));
        sprites.add(getImageResource("/sprites/tiles/grounds/" + name + "/top_joint.png"));
        sprites.add(getImageResource("/sprites/tiles/grounds/" + name + "/right_joint.png"));
        sprites.add(getImageResource("/sprites/tiles/grounds/" + name + "/bottom_side.png"));
        sprites.add(getImageResource("/sprites/tiles/grounds/" + name + "/left_side.png"));
        sprites.add(getImageResource("/sprites/tiles/grounds/" + name + "/top_side.png"));
        sprites.add(getImageResource("/sprites/tiles/grounds/" + name + "/right_side.png"));
        sprites.add(getImageResource("/sprites/tiles/grounds/" + name + "/top_left_corner.png"));
        sprites.add(getImageResource("/sprites/tiles/grounds/" + name + "/top_right_corner.png"));
        sprites.add(getImageResource("/sprites/tiles/grounds/" + name + "/bottom_left_corner.png"));
        sprites.add(getImageResource("/sprites/tiles/grounds/" + name + "/bottom_right_corner.png"));
        sprites.add(getImageResource("/sprites/tiles/grounds/" + name + "/horizontal_joint.png"));
        sprites.add(getImageResource("/sprites/tiles/grounds/" + name + "/vertical_joint.png"));
    }

    @Override
    public void tick() {

    }

    @Override
    public void renderForPlayer(Graphics2D graphics, FOV fov) {
        // Rendering efficiency (don't render tiles if they're outside the screen)
        if (xEnd < fov.getxStart() || xStart > fov.getxEnd() || yEnd < fov.getyStart() || yStart > fov.getyEnd()) return;

        graphics.drawImage(sprites.get(position), (int) (xStart + fov.getTotalXOffset()), (int) (yStart + fov.getTotalYOffset()), width, height, null);
    }

}
