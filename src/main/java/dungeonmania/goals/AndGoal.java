package dungeonmania.goals;

import org.json.JSONArray;
import org.json.JSONObject;

import dungeonmania.Game;

public class AndGoal implements Goal {
    private Goal goal1;
    private Goal goal2;

    public AndGoal(Goal goal1, Goal goal2) {
        this.goal1 = goal1;
        this.goal2 = goal2;
    }

    @Override
    public boolean achieved(Game game) {
        // if (game.getPlayer() == null)
        // return false;
        return goal1.achieved(game) && goal2.achieved(game);
    }

    @Override
    public String toString(Game game) {
        if (this.achieved(game))
            return "";
        return "(" + goal1.toString(game) + " AND " + goal2.toString(game) + ")";
    }

    @Override
    public JSONObject goalsToJSON() {
        JSONObject j = new JSONObject();
        j.put("goal", "AND");
        JSONArray subgoals = new JSONArray();
        subgoals.put(goal1.goalsToJSON());
        subgoals.put(goal2.goalsToJSON());
        j.put("subgoals", subgoals);
        return j;
    }
}
