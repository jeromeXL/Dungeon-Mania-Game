package dungeonmania.goals;

import dungeonmania.Game;
import dungeonmania.entities.collectables.Treasure;

public class TreasureGoal implements Goal {
    int target;

    public TreasureGoal(int target) {
        this.target = target;
    }

    @Override
    public boolean achieved(Game game) {
        if (game.getPlayer() == null)
            return false;
        return game.getInitialTreasureCount() - game.getMap().getEntities(Treasure.class).size() >= target;
    }

    @Override
    public String toString(Game game) {
        if (this.achieved(game))
            return "";
        return ":treasure";
    }
}