package me.thatonerr.slayone.blocks;

import me.thatonerr.slayone.Game;
import me.thatonerr.slayone.framework.FOV;
import me.thatonerr.slayone.framework.Hitbox;
import me.thatonerr.slayone.values.TileType;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static me.thatonerr.slayone.utilities.Utils.getImageResource;

public class Boundary extends Block {

    private static final List<BufferedImage> sprites = new ArrayList<>();
    private int yShift = (int) (60 * 1.5);

    public Boundary(Game game, int position, int tileX, int tileY) {
        super(new Hitbox(0, 0, 60, 60), TileType.BOUNDARY, game, position, tileX, tileY, 60, 60);
        switch (position) {
            case 1:
            case 7:
            case 8:
            case 11:
            case 12:
            case 13:
                height += yShift;
                updatePosition();
                break;
        }
    }

    public static void getSprites() {
        String name = "boundary";
        sprites.add(getImageResource("/sprites/tiles/blocks/" + name + "/all_joint.png"));
        sprites.add(getImageResource("/sprites/tiles/blocks/" + name + "/bottom_side.png"));
        sprites.add(getImageResource("/sprites/tiles/blocks/" + name + "/left_side.png"));
        sprites.add(getImageResource("/sprites/tiles/blocks/" + name + "/top_side.png"));
        sprites.add(getImageResource("/sprites/tiles/blocks/" + name + "/right_side.png"));
        sprites.add(getImageResource("/sprites/tiles/blocks/" + name + "/top_left_corner.png"));
        sprites.add(getImageResource("/sprites/tiles/blocks/" + name + "/top_right_corner.png"));
        sprites.add(getImageResource("/sprites/tiles/blocks/" + name + "/bottom_left_corner.png"));
        sprites.add(getImageResource("/sprites/tiles/blocks/" + name + "/bottom_right_corner.png"));
        sprites.add(getImageResource("/sprites/tiles/blocks/" + name + "/top_left_corner_joint.png"));
        sprites.add(getImageResource("/sprites/tiles/blocks/" + name + "/top_right_corner_joint.png"));
        sprites.add(getImageResource("/sprites/tiles/blocks/" + name + "/bottom_left_corner_joint.png"));
        sprites.add(getImageResource("/sprites/tiles/blocks/" + name + "/bottom_right_corner_joint.png"));
        sprites.add(getImageResource("/sprites/tiles/blocks/" + name + "/bottom_side_top.png"));
    }

    @Override
    public int getMovability(float incVelX, float incVelY) {
        return 0;
    }

    @Override
    public void tick() {

    }

    @Override
    public void renderForPlayer(Graphics2D graphics, FOV fov) {
        // Rendering efficiency (don't render tiles if they're outside the screen)
        if (xEnd < fov.getxStart() || xStart > fov.getxEnd() || yEnd - yShift < fov.getyStart() || yStart - yShift > fov.getyEnd()) return;

        graphics.drawImage(sprites.get(position), (int) (xStart + fov.getTotalXOffset()), (int) (yStart + fov.getTotalYOffset() - yShift), width, height, null);

        // Hitbox
//        graphics.setColor(new Color(60, 60, 250, 100));
//        graphics.fillRect((int) xStart + hitbox.getX() + fov.getTotalXOffset(), (int) yStart + hitbox.getY() + fov.getTotalYOffset(), hitbox.getWidth(), hitbox.getHeight());
    }

}
