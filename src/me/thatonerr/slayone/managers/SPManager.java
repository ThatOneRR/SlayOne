package me.thatonerr.slayone.managers;

import me.thatonerr.slayone.Game;
import me.thatonerr.slayone.entities.Player;
import me.thatonerr.slayone.framework.GameMap;

import java.awt.*;

public class SPManager {

    private Game game;
    private GameMap gameMap;
    private Player player;

    public SPManager(Game game) {
        this.game = game;
    }

    public void startLevel() {
        player = new Player(game, 3 * 60, 3 * 60);
        gameMap = new GameMap(game, player, "test_level");
        player.setGameMap(gameMap);
        player.setBlockMap(gameMap.getBlockMap());
    }

    public void tick() {
        gameMap.tick();
    }

    public void render(Graphics2D graphics) {
        gameMap.render(graphics);
    }

}
