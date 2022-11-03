package dungeonmania.goals;

import dungeonmania.Game;
import dungeonmania.map.GameMap;

public interface Goal {
    public boolean achieved(Game game);

    public String toString(Game game);

}
