package dungeonmania.entities.logicals;

import dungeonmania.util.Position;

import dungeonmania.map.GameMap;

public class LightBulbOff extends LogicalEntity {

    public LightBulbOff(Position position) {
        super(position);
    }

    @Override
    public void updateStatus(GameMap map) {
        if (this.isActivated(map)) {
            map.addEntity(new LightBulbOn(getPosition()));
            map.removeNode(this);
       } else {
       }
    }
}
