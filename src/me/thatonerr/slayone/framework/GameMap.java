package me.thatonerr.slayone.framework;

import me.thatonerr.slayone.Game;
import me.thatonerr.slayone.blocks.Block;
import me.thatonerr.slayone.entities.Entity;
import me.thatonerr.slayone.entities.Player;
import me.thatonerr.slayone.grounds.Ground;
import me.thatonerr.slayone.projectiles.Projectile;
import me.thatonerr.slayone.utilities.MapLoader;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameMap {

    private Game game;
    private Player player;

    private Map<Integer, Map<Integer, Ground>> groundMap;
    private Map<Integer, Map<Integer, Block>> blockMap;
    private List<Entity> entities = new ArrayList<>();

    public GameMap(Game game, Player player, String folderName) {
        this.game = game;
        this.player = player;
        groundMap = MapLoader.loadGroundMap(game, folderName);
        blockMap = MapLoader.loadBlockMap(game, folderName);
        entities.add(player);
    }

    public void tick() {
        for (Map<Integer, Ground> groundRow : groundMap.values()) {
            for (Ground ground : groundRow.values()) {
                if (ground == null) continue;
                ground.tick();
            }
        }
        for (Map<Integer, Block> blockRow : blockMap.values()) {
            for (Block block : blockRow.values()) {
                if (block == null) continue;
                block.tick();
            }
        }
        player.tick();
        for (Entity entity : entities) {
            if (entity instanceof Player) continue;
            entity.tick();
        }
    }

    public void render(Graphics2D graphics) {
        for (Map<Integer, Ground> groundRow : groundMap.values()) {
            for (Ground ground : groundRow.values()) {
                if (ground == null) continue;
                ground.renderForPlayer(graphics, player.getFov());
            }
        }
        List<Entity> afterEntities = new ArrayList<>();
        for (Entity entity : entities) {
            if (entity instanceof Player) continue;
            if (entity instanceof Projectile) {
                Projectile projectile = (Projectile) entity;
                try {
                    if (blockMap.get((int) projectile.getYStart() / 60 + 1).get((int) projectile.getXStart() / 60) != null || blockMap.get((int) (projectile.getYStart() + 150) / 60).get((int) projectile.getXStart() / 60) != null || blockMap.get((int) projectile.getYStart() / 60 + 1).get((int) projectile.getXEnd() / 60) != null || blockMap.get((int) (projectile.getYStart() + 150) / 60).get((int) projectile.getXEnd() / 60) != null || blockMap.get((int) projectile.getYEnd() / 60 + 1).get((int) projectile.getXStart() / 60) != null || blockMap.get((int) (projectile.getYEnd() + 150) / 60).get((int) projectile.getXStart() / 60) != null || blockMap.get((int) projectile.getYEnd() / 60 + 1).get((int) projectile.getXEnd() / 60) != null || blockMap.get((int) (projectile.getYEnd() + 150) / 60).get((int) projectile.getXEnd() / 60) != null) {
                        projectile.renderForPlayer(graphics, player.getFov());
                    } else {
                        afterEntities.add(projectile);
                    }
                } catch (NullPointerException ignored) {
                    // If the entity is outside of bound, don't crash da game mate.
                }
            }
        }
        for (Map<Integer, Block> blockRow : blockMap.values()) {
            for (Block block : blockRow.values()) {
                if (block == null || block.getTileY() > player.getYEnd() / 60) continue;
                block.renderForPlayer(graphics, player.getFov());
            }
        }
        player.render(graphics);
        for (Map<Integer, Block> blockRow : blockMap.values()) {
            for (Block block : blockRow.values()) {
                if (block == null || block.getTileY() < player.getYEnd() / 60) continue;
                block.renderForPlayer(graphics, player.getFov());
            }
        }
        for (Entity entity : afterEntities) {
            if (entity instanceof Projectile) {
                Projectile projectile = (Projectile) entity;
                projectile.renderForPlayer(graphics, player.getFov());
            }
        }
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public Map<Integer, Map<Integer, Ground>> getGroundMap() {
        return groundMap;
    }

    public Map<Integer, Map<Integer, Block>> getBlockMap() {
        return blockMap;
    }

}
