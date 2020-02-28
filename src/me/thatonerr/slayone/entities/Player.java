package me.thatonerr.slayone.entities;

import me.thatonerr.slayone.Game;
import me.thatonerr.slayone.blocks.Block;
import me.thatonerr.slayone.framework.FOV;
import me.thatonerr.slayone.framework.GameMap;
import me.thatonerr.slayone.framework.Hitbox;
import me.thatonerr.slayone.guns.Gun;
import me.thatonerr.slayone.guns.LaserPistol;
import me.thatonerr.slayone.info.Character;
import me.thatonerr.slayone.info.Skin;
import me.thatonerr.slayone.listeners.KeyInput;
import me.thatonerr.slayone.listeners.MouseInput;
import me.thatonerr.slayone.values.KeyControl;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Player extends LivingEntity {

    private int moveDirection = 0;
    private Character character;
    private Skin skin;
    private FOV fov;
    private MouseInput mouse;
    private KeyInput keyboard;
    private GameMap gameMap;
    private List<Gun> guns = new ArrayList<>();
    private Gun gun = new LaserPistol();
    private int recoilAmount = 0, recoilStatus = 0;

    private Map<Integer, Map<Integer, Block>> blockMap;

    public Player(Game game, float xStart, float yStart) {
        super(0, 100, 100, new Hitbox(10, 7, 45, 90), new Hitbox(-4, 45, 72, 51), game, xStart, yStart, 64, 96);
        character = Character.getInstance();
        skin = character.getSkin();
        fov = new FOV(0, 0, 0, 0);
        mouse = game.getMouseInput();
        keyboard = game.getKeyInput();
    }

    @Override
    public int getMovability(float incVelX, float incVelY) {
        return 5;
    }

    @Override
    public void tick() {
        // Updating field of vision based on mouse movement and player displacement
        fov.setxOffset((int) (game.getWidth() / 2 - xStart - width / 2));
        fov.setyOffset((int) (game.getHeight() / 2 - yStart - height / 2));

        int mouseX = mouse.getMouseX(), mouseY = mouse.getMouseY();
        fov.setMouseXOffset((mouseX - game.getWidth() / 2) / 2);
        fov.setMouseYOffset((mouseY - game.getHeight() / 2) / 2);

        fov.setTotalXOffset(fov.getxOffset() - fov.getMouseXOffset());
        fov.setTotalYOffset(fov.getyOffset() - fov.getMouseYOffset());

        fov.setxStart((int) (xStart + width / 2 - game.getWidth() / 2) + fov.getMouseXOffset());
        fov.setxEnd((int) (xStart + width / 2 + game.getWidth() / 2) + fov.getMouseXOffset());
        fov.setyStart((int) (yStart + height / 2 - game.getHeight() / 2) + fov.getMouseYOffset());
        fov.setyEnd((int) (yStart + height / 2 + game.getHeight() / 2) + fov.getMouseYOffset());

        // Determining direction based on mouse angle
        // Screen is divided into four quadrants, each quadrant has a 22.5-45-22.5 degree angle rotation.
        // This ensures that the 4 cardinal and 4 diagonals have an equal angle each (of 45)
        int newDirection = 0;
        float angle = (float) Math.toDegrees(Math.atan2(game.getHeight() / 2f - mouseY, game.getWidth() / 2f - mouseX));
        if (angle < 0) angle += 360;
        if (angle >= 247.5 && angle < 292.5) {
            newDirection = 0;
        } else if (angle >= 292.5 && angle < 337.5) {
            newDirection = 1;
        } else if (angle >= 337.5 && angle <= 360 || angle >= 0 && angle < 22.5) {
            newDirection = 2;
        } else if (angle >= 22.5 && angle < 67.5) {
            newDirection = 3;
        } else if (angle >= 67.5 && angle < 112.5) {
            newDirection = 4;
        } else if (angle >= 112.5 && angle < 157.5) {
            newDirection = 5;
        } else if (angle >= 157.5 && angle < 202.5) {
            newDirection = 6;
        } else if (angle >= 202.5 && angle < 247.5) {
            newDirection = 7;
        }

        // Carrying over the previous frame position of the old animation to the new animation
        if (newDirection != direction) {
            skin.getHeadAnims().get(newDirection).setIndex(skin.getHeadAnims().get(direction).getIndex());
            skin.getBodyAnims().get(newDirection).setIndex(skin.getBodyAnims().get(direction).getIndex());
        }
        direction = newDirection;

        // Detecting movement from key/mouse inputs
        List<Integer> codes = new ArrayList<>(mouse.getActiveKeys().keySet());
        codes.addAll(keyboard.getActiveKeys().keySet());
        boolean left = false, right = false, up = false, down = false;
        for (int keyCode : codes) {
            if (keyCode == KeyControl.MOVE_UP.getKeyCode()) {
                up = true;
            } else if (keyCode == KeyControl.MOVE_DOWN.getKeyCode()) {
                down = true;
            } else if (keyCode == KeyControl.MOVE_LEFT.getKeyCode()) {
                left = true;
            } else if (keyCode == KeyControl.MOVE_RIGHT.getKeyCode()) {
                right = true;
            }
        }
        // Combining multiple keys to achieve multi-directional movement
        int nowMoveDirection = -1;
        float moveSpeed = 5, moveDiaSpeed = 4;
        if (up && down || !up && !down) {
            velY = 0;
        } else if (up) {
            nowMoveDirection = 4;
            velY = -moveSpeed;
        } else {
            nowMoveDirection = 0;
            velY = moveSpeed;
        }
        if (left && right || !left && !right) {
            velX = 0;
        } else if (left) {
            nowMoveDirection = 2;
            if (down) {
                nowMoveDirection = 1;
                velY = moveDiaSpeed;
            } else if (up) {
                nowMoveDirection = 3;
                velY = -moveDiaSpeed;
            }
            velX = -moveSpeed;
            if (up || down) {
                velX = -moveDiaSpeed;
            }
        } else {
            nowMoveDirection = 6;
            if (down) {
                nowMoveDirection = 7;
                velY = moveDiaSpeed;
            } else if (up) {
                nowMoveDirection = 5;
                velY = -moveDiaSpeed;
            }
            velX = moveSpeed;
            if (up || down) {
                velX = moveDiaSpeed;
            }
        }
        if (nowMoveDirection != -1) {
            skin.getBodyAnims().get(nowMoveDirection).setIndex(skin.getBodyAnims().get(moveDirection).getIndex());
        }
        moveDirection = nowMoveDirection;

        yStart += velY;
        checkCollisionY();

        xStart += velX;
        checkCollisionX();

        updatePosition();

        if (moveDirection != -1) {
            skin.getBodyAnims().get(moveDirection).tick();
        } else {
            // Default Idle position is frame 3 of animation 1 (looking down)
            moveDirection = 0;
            skin.getBodyAnims().get(0).setIndex(3);
        }
        skin.getHeadAnims().get(direction).tick();
        skin.getLeftHandAnim().tick();
        skin.getRightHandAnim().tick();

        // Ticking gun position
        gun.setDirection(direction);

        // Gun recoil
        boolean shoot = false;
        for (int keyCode : codes) {
            if (keyCode == KeyControl.SHOOT.getKeyCode()) shoot = true;
        }
        handleGunshot(shoot, angle);
    }

    private void handleGunshot(boolean shoot, float angle) {
        gun.tick();
        if (shoot && recoilStatus == 0 && gun.getReload() == gun.getReloadSpeed()) {
            recoilStatus = 1;
            int leftHandX = (int) xStart + 41, leftHandY = (int) yStart + 48 + recoilAmount, rightHandX = (int) xStart + 22, rightHandY = (int) yStart + 54 + recoilAmount;
            switch (direction) {
                case 1:
                    leftHandX = (int) xStart + 30 - recoilAmount;
                    leftHandY = (int) yStart + 48 + recoilAmount;
                    rightHandX = (int) xStart + 5 - recoilAmount;
                    rightHandY = (int) yStart + 58 + recoilAmount;
                    break;
                case 2:
                    leftHandX = (int) xStart + 9 - recoilAmount;
                    leftHandY = (int) yStart + 60;
                    rightHandX = (int) xStart - 12 - recoilAmount;
                    rightHandY = (int) yStart + 38;
                    break;
                case 3:
                    leftHandX = (int) xStart - recoilAmount;
                    leftHandY = (int) yStart + 57 - recoilAmount;
                    rightHandX = -1;
                    rightHandY = -1;
                    break;
                case 4:
                    leftHandX = -1;
                    leftHandY = -1;
                    rightHandX = -1;
                    rightHandY = -1;
                    break;
                case 5:
                    leftHandX = -1;
                    leftHandY = -1;
                    rightHandX = (int) xStart + 35 + recoilAmount;
                    rightHandY = (int) yStart + 57 - recoilAmount;
                    break;
                case 6:
                    leftHandX = (int) xStart + 43 + recoilAmount;
                    leftHandY = (int) yStart + 38;
                    rightHandX = (int) xStart + 20 + recoilAmount;
                    rightHandY = (int) yStart + 60;
                    break;
                case 7:
                    leftHandX = (int) xStart + 40 + recoilAmount;
                    leftHandY = (int) yStart + 50 + recoilAmount;
                    rightHandX = (int) xStart + 12 + recoilAmount;
                    rightHandY = (int) yStart + 53 + recoilAmount;
                    break;
            }

            // Drawing equipped gun
            int gunX = (int) xStart + width / 2, gunY = (int) yStart + 10;
            switch (direction) {
                case 0:
                    gunX = rightHandX + 20;
                    gunY = rightHandY - 5;
                    break;
                case 1:
                    gunX = rightHandX + 5;
                    gunY = leftHandY - 5;
                    break;
                case 2:
                    gunX = leftHandX + 20;
                    gunY = leftHandY + (leftHandY - rightHandY) / 2;
                    break;
                case 3:
                    gunX = leftHandX + 20;
                    gunY = leftHandY + 15;
                    break;
                case 5:
                    gunX = rightHandX;
                    gunY = rightHandY + 20;
                    break;
                case 6:
                    gunX = rightHandX + 5;
                    gunY = rightHandY + (leftHandY - rightHandY) / 2;
                    break;
                case 7:
                    gunX = rightHandX + 15;
                    gunY = rightHandY - 5;
                    break;
            }
            gun.shoot(gameMap, game, angle, gunX, gunY);
        } else if (recoilStatus != 0) {
            if (recoilStatus == 1) {
                if (recoilAmount > -gun.getRecoilSpeedMax()) {
                    recoilAmount -= gun.getRecoilSpeed();
                } else {
                    recoilStatus++;
                }
            } else if (recoilStatus == 2) {
                if (recoilAmount < 0) {
                    recoilAmount += gun.getRecoilUndoSpeed();
                } else {
                    recoilStatus = 0;
                }
            }
        }
    }

    private void checkCollisionX() {
        if (velX > 0) {
            // Moving right
            Block topBlock = blockMap.get((int) (yStart + tileHitbox.getY()) / 60).get((int) (xStart + tileHitbox.getX() + tileHitbox.getWidth() - 1) / 60);
            Block bottomBlock = blockMap.get((int) (yStart + tileHitbox.getY() + tileHitbox.getHeight() - 1) / 60).get((int) (xStart + tileHitbox.getX() + tileHitbox.getWidth() - 1) / 60);
            float finalX = 0;
            if (topBlock != null) {
                Hitbox blockHB = topBlock.getHitbox();
                finalX = topBlock.getXStart() + blockHB.getX() - tileHitbox.getWidth() + 1;
            }
            if (bottomBlock != null) {
                Hitbox blockHB = bottomBlock.getHitbox();
                float thisX = bottomBlock.getXStart() + blockHB.getX() - tileHitbox.getWidth() + 1;
                if (thisX < finalX) finalX = thisX;
                if (finalX == 0) finalX = thisX;
            }
            if (finalX != 0) {
                xStart = finalX;
            }
        } else if (velX < 0) {
            // Moving left
            Block topBlock = blockMap.get((int) (yStart + tileHitbox.getY()) / 60).get((int) (xStart + tileHitbox.getX()) / 60);
            Block bottomBlock = blockMap.get((int) (yStart + tileHitbox.getY() + tileHitbox.getHeight() - 1) / 60).get((int) (xStart + tileHitbox.getX()) / 60);
            float finalX = 0;
            if (topBlock != null) {
                Hitbox blockHB = topBlock.getHitbox();
                finalX = topBlock.getXStart() + blockHB.getX() + blockHB.getWidth() - tileHitbox.getX();
            }
            if (bottomBlock != null) {
                Hitbox blockHB = bottomBlock.getHitbox();
                float thisX = bottomBlock.getXStart() + blockHB.getX() + blockHB.getWidth() - tileHitbox.getX();
                if (thisX > finalX) finalX = thisX;
                if (finalX == 0) finalX = thisX;
            }
            if (finalX != 0) {
                xStart = finalX;
            }
        }
    }

    private void checkCollisionY() {
        if (velY > 0) {
            // Going down
            Block leftBlock = blockMap.get((int) (yStart + tileHitbox.getY() + tileHitbox.getHeight() - 1) / 60).get((int) (xStart + tileHitbox.getX()) / 60);
            Block rightBlock = blockMap.get((int) (yStart + tileHitbox.getY() + tileHitbox.getHeight() - 1) / 60).get((int) (xStart + tileHitbox.getX() + tileHitbox.getWidth() - 1) / 60);
            float finalY = 0;
            if (leftBlock != null) {
                Hitbox blockHB = leftBlock.getHitbox();
                finalY = leftBlock.getYStart() + blockHB.getY() - tileHitbox.getY() - tileHitbox.getHeight();
            }
            if (rightBlock != null) {
                Hitbox blockHB = rightBlock.getHitbox();
                float thisY = rightBlock.getYStart() + blockHB.getY() - tileHitbox.getY() - tileHitbox.getHeight();
                if (thisY < finalY) finalY = thisY;
                if (finalY == 0) finalY = thisY;
            }
            if (finalY != 0) {
                yStart = finalY;
            }
        } else if (velY < 0) {
            // Going up
            Block leftBlock = blockMap.get((int) (yStart + tileHitbox.getY()) / 60).get((int) (xStart + tileHitbox.getX()) / 60);
            Block rightBlock = blockMap.get((int) (yStart + tileHitbox.getY()) / 60).get((int) (xStart + tileHitbox.getX() + tileHitbox.getWidth() - 1) / 60);
            float finalY = 0;
            if (leftBlock != null) {
                Hitbox blockHB = leftBlock.getHitbox();
                finalY = leftBlock.getYStart() + blockHB.getY() + blockHB.getHeight() - tileHitbox.getY();
            }
            if (rightBlock != null) {
                Hitbox blockHB = rightBlock.getHitbox();
                float thisY = rightBlock.getYStart() + blockHB.getY() + blockHB.getHeight() - tileHitbox.getY();
                if (thisY > finalY) finalY = thisY;
                if (finalY == 0) finalY = thisY;
            }
            if (finalY != 0) {
                yStart = finalY;
            }
        }
    }

    @Override
    public void render(Graphics2D graphics) {
        int xOffset = fov.getTotalXOffset(), yOffset = fov.getTotalYOffset();

        // Precise placing of each hand based on the current direction.
        int leftHandX = (int) xStart + 41 + xOffset, leftHandY = (int) yStart + 48 + yOffset + recoilAmount, rightHandX = (int) xStart + 22 + xOffset, rightHandY = (int) yStart + 54 + yOffset + recoilAmount;
        switch (direction) {
            case 1:
                leftHandX = (int) xStart + 30 + xOffset - recoilAmount;
                leftHandY = (int) yStart + 48 + yOffset + recoilAmount;
                rightHandX = (int) xStart + 5 + xOffset - recoilAmount;
                rightHandY = (int) yStart + 58 + yOffset + recoilAmount;
                break;
            case 2:
                leftHandX = (int) xStart + 9 + xOffset - recoilAmount;
                leftHandY = (int) yStart + 60 + yOffset;
                rightHandX = (int) xStart - 12 + xOffset - recoilAmount;
                rightHandY = (int) yStart + 38 + yOffset;
                break;
            case 3:
                leftHandX = (int) xStart + xOffset - recoilAmount;
                leftHandY = (int) yStart + 57 + yOffset - recoilAmount;
                rightHandX = -1;
                rightHandY = -1;
                break;
            case 4:
                leftHandX = -1;
                leftHandY = -1;
                rightHandX = -1;
                rightHandY = -1;
                break;
            case 5:
                leftHandX = -1;
                leftHandY = -1;
                rightHandX = (int) xStart + 35 + xOffset + recoilAmount;
                rightHandY = (int) yStart + 57 + yOffset - recoilAmount;
                break;
            case 6:
                leftHandX = (int) xStart + 43 + xOffset + recoilAmount;
                leftHandY = (int) yStart + 38 + yOffset;
                rightHandX = (int) xStart + 20 + xOffset + recoilAmount;
                rightHandY = (int) yStart + 60 + yOffset;
                break;
            case 7:
                leftHandX = (int) xStart + 40 + xOffset + recoilAmount;
                leftHandY = (int) yStart + 50 + yOffset + recoilAmount;
                rightHandX = (int) xStart + 12 + xOffset + recoilAmount;
                rightHandY = (int) yStart + 53 + yOffset + recoilAmount;
                break;
        }

        // Drawing equipped gun
        int gunX = 0, gunY = 0, displaceX = 0, displaceY = 0;
        switch (direction) {
            case 0:
                gunX = rightHandX + 20;
                gunY = rightHandY - 5;
                break;
            case 1:
                gunX = rightHandX + 5;
                gunY = leftHandY - 5;
                break;
            case 2:
                gunX = leftHandX + 20;
                gunY = leftHandY + (leftHandY - rightHandY) / 2;
                displaceX = 1;
                displaceY = 1;
                break;
            case 3:
                gunX = leftHandX + 20;
                gunY = leftHandY + 15;
                displaceX = 1;
                displaceY = 1;
                break;
            case 5:
                gunX = rightHandX;
                gunY = rightHandY + 20;
                displaceY = 1;
                break;
            case 6:
                gunX = rightHandX + 5;
                gunY = rightHandY + (leftHandY - rightHandY) / 2;
                break;
            case 7:
                gunX = rightHandX + 15;
                gunY = rightHandY - 5;
                break;
        }
        if (direction == 3 || direction == 5) gun.render(graphics, fov, gunX, gunY, displaceX, displaceY);

        // Head and body. Head is overlaid over the body.
        graphics.drawImage(skin.getBodyAnims().get(moveDirection).getCurrentFrame(), (int) xStart - 21 + xOffset, (int) yStart + 52 + yOffset, 108, 64, null);
        graphics.drawImage(skin.getHeadAnims().get(direction).getCurrentFrame(), (int) xStart - 15 + xOffset, (int) yStart - 15 + yOffset, 96, 96, null);

        if (direction < 4) {
            if (rightHandX >= 0 && rightHandY >= 0) {
                graphics.drawImage(skin.getRightHandAnim().getCurrentFrame(), rightHandX, rightHandY, 28, 28, null);
            }
            gun.render(graphics, fov, gunX, gunY, displaceX, displaceY);
            if (leftHandX >= 0 && leftHandY >= 0) {
                graphics.drawImage(skin.getLeftHandAnim().getCurrentFrame(), leftHandX, leftHandY, 28, 28, null);
            }
        } else {
            if (leftHandX >= 0 && leftHandY >= 0) {
                graphics.drawImage(skin.getLeftHandAnim().getCurrentFrame(), leftHandX, leftHandY, 28, 28, null);
            }
            gun.render(graphics, fov, gunX, gunY, displaceX, displaceY);
            if (rightHandX >= 0 && rightHandY >= 0) {
                graphics.drawImage(skin.getRightHandAnim().getCurrentFrame(), rightHandX, rightHandY, 28, 28, null);
            }
        }
        gun.renderHUD(graphics, fov, (int) xStart + width / 2, (int) yStart);

        // Showing entity hitbox and tile hitbox
//        graphics.setColor(new Color(250, 60, 60, 100));
//        graphics.fillRect((int) xStart + hitbox.getX() + xOffset, (int) yStart + hitbox.getY() + yOffset, hitbox.getWidth(), hitbox.getHeight());
//        graphics.setColor(new Color(60, 60, 250, 100));
//        graphics.fillRect((int) xStart + tileHitbox.getX() + xOffset, (int) yStart + tileHitbox.getY() + yOffset, tileHitbox.getWidth(), tileHitbox.getHeight());
    }

    public FOV getFov() {
        return fov;
    }

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public void setBlockMap(Map<Integer, Map<Integer, Block>> blockMap) {
        this.blockMap = blockMap;
    }

}
