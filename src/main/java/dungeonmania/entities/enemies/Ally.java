package dungeonmania.entities.enemies;

import dungeonmania.map.GameMap;

public interface Ally {
    public boolean isAllied();

    public void setAllied();

    public void isAdjacentToPlayer(GameMap map);
}
