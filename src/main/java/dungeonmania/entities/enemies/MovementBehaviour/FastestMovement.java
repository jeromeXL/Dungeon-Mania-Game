package dungeonmania.entities.enemies.MovementBehaviour;

import dungeonmania.Game;
import dungeonmania.entities.enemies.Enemy;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class FastestMovement implements Movement {
    Enemy enemy;

    public FastestMovement(Enemy enemy) {
        this.enemy = enemy;
    }

    @Override
    public void move(Game game, GameMap map) {
        Position nextPos = map.dijkstraPathFind(enemy.getPosition(), map.getPlayerCurrPos(), enemy);
        map.moveTo(enemy, nextPos);
    }
}
