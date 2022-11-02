package dungeonmania.goals;

import org.json.JSONObject;

import dungeonmania.Game;
import dungeonmania.entities.enemies.ZombieToastSpawner;

public class EnemyGoal implements Goal {
    private int target;

    public EnemyGoal(int target) {
        this.target = target;
    }

    @Override
    public boolean achieved(Game game) {
        // if (game.getPlayer() == null)
        // return false;
        return game.getPlayer().getKillCount() >= target && game.countEntities(ZombieToastSpawner.class) <= 0;
    }

    @Override
    public String toString(Game game) {
        if (this.achieved(game))
            return "";
        return ":enemies";
    }

    @Override
    public JSONObject goalsToConfig() {
        JSONObject j = new JSONObject();
        j.put("goal", "enemies");
        return j;
    }
}
