package dungeonmania.entities.enemies.MovementBehaviour;

import dungeonmania.entities.enemies.Enemy;
import dungeonmania.map.GameMap;
import dungeonmania.Game;

public class NoMovement implements Movement {
    private Enemy enemy;

    public NoMovement(Enemy enemy) {
        this.enemy = enemy;
    }

    @Override
    public void move(Game game, GameMap map) {
        return;
    }
}
