package dungeonmania.goals;

import java.util.List;

import dungeonmania.Game;
import dungeonmania.entities.Entity;
import dungeonmania.entities.Exit;
import dungeonmania.entities.Player;
import dungeonmania.entities.Switch;
import dungeonmania.entities.collectables.Treasure;
import dungeonmania.util.Position;

public interface Goal {
    public boolean achieved(Game game);

    public String toString(Game game);
}
