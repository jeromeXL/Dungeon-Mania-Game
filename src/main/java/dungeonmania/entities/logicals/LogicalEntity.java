package dungeonmania.entities.logicals;

import dungeonmania.map.GameMap;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.entities.Entity;
import dungeonmania.util.Position;

import dungeonmania.map.GameMap;;

public class LogicalEntity extends Entity {

    public LogicalEntity(Position position) {
        super(position);
    }

    public boolean isActivated(GameMap map) {

        // All logical Entities
        List<LogicalEntity> LogicalEntities = map.getEntities(LogicalEntity.class);

        // Check if they are adjacent and are activated
        for (LogicalEntity l: LogicalEntities) {
            if (Position.isAdjacent(this.getPosition(), l.getPosition()) && l.isActivated(map)) {
                return true;
            }
        }
        return false;
    }

}
