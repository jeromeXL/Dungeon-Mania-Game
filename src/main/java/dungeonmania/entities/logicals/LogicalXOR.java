package dungeonmania.entities.logicals;

import java.util.List;

import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class LogicalXOR implements LogicalStrategy {
    private LogicalEntity e;

    public LogicalXOR(LogicalEntity e) {
        this.e = e;
    }

    @Override
    public boolean isActivated(GameMap map) {
        int count = 0;
        // All logical Entities
        List<LogicalEntity> LogicalEntities = map.getEntities(LogicalEntity.class);

        // Check if they are adjacent and are activated
        for (LogicalEntity l : LogicalEntities) {
            if (l != e && Position.isAdjacent(e.getPosition(), l.getPosition()) && l.isActivated(map)) {
                count++;
            }
        }
        return count == 1;
    }

    @Override
    public String toString() {
        return "xor";
    }
}
