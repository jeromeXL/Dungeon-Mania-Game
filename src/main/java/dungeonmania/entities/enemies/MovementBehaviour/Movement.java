package dungeonmania.entities.enemies.MovementBehaviour;

import dungeonmania.Game;
import dungeonmania.map.GameMap;

public interface Movement {
    public void move(Game game, GameMap map);
}
