package dungeonmania.entities.logicals;

import java.util.List;
import java.util.stream.Collectors;

import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class LogicalCOAND implements LogicalStrategy {
    private LogicalEntity e;

    public LogicalCOAND(LogicalEntity e) {
        this.e = e;
    }

    @Override
    public boolean isActivated(GameMap map) {
        // Adjacent Logical Entities
        List<LogicalEntity> AdjacentLogEnt = map.getEntities(LogicalEntity.class).stream()
                .filter(l -> l != e && Position.isAdjacent(e.getPosition(), l.getPosition()))
                .collect(Collectors.toList());

        int numLogEntActive = (int) AdjacentLogEnt.stream().filter(l -> l.isActivated(map)).count();
        if (numLogEntActive < 2) {
            return false;
        }
        int tickActivated = AdjacentLogEnt.get(0).getActivatedTick();
        return AdjacentLogEnt.stream().allMatch(l -> l.getActivatedTick() == tickActivated);
    }

    @Override
    public String toString() {
        return "co_and";
    }
}
