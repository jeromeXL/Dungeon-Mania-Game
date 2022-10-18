package dungeonmania.entities.enemies.MovementBehaviour;

import dungeonmania.entities.enemies.Enemy;
import dungeonmania.Game;

public class NoMovement implements Movement {
    Enemy enemy;

    public NoMovement(Enemy enemy) {
        this.enemy = enemy;
    }

    @Override
    public void move(Game game) {
        return;
    }
}
