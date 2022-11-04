package dungeonmania.entities.logicals;

import dungeonmania.util.Position;

import dungeonmania.map.GameMap;

public class LightBulbOn extends LogicalEntity {

    public LightBulbOn(Position position, String logic) {
        super(position, logic);
    }

    @Override
    public void updateStatus(GameMap map) {
        if (!this.isActivated(map)) {
            map.addEntity(new LightBulbOff(this.getPosition(), logicType()));
            map.removeNode(this);
        } else {
        }
    }
}