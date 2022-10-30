package dungeonmania.entities.buildables;

import dungeonmania.Game;
import dungeonmania.battles.BattleStatistics;

public class Sceptre extends Buildable {
    private int mind_control_duration;
    public Sceptre(int mind_control_duration) {
        super(null);
        this.mind_control_duration = mind_control_duration;
    }

    @Override
    public void use(Game game) {
        // This has infinite durability so it should do nothing
        return;
    }

    @Override
    public int getDurability() {
        return 1;
    }

    @Override
    public BattleStatistics applyBuff(BattleStatistics origin) {
        // TODO Auto-generated method stub
        return null;
    }

    public int getMindControlDuration() {
        return mind_control_duration;
    }
}
