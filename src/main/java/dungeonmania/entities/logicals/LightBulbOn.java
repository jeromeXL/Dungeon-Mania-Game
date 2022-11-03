package dungeonmania.entities.logicals;

import dungeonmania.util.Position;

import dungeonmania.map.GameMap;

public class LightBulbOn extends LogicalEntity {

    public LightBulbOn(Position position) {
        super(position);
    }

    @Override
    public void updateStatus(GameMap map) {
        if (! this.isActivated(map)) {
            map.addEntity(new LightBulbOff(this.getPosition()));
            map.removeNode(this);
       } else {
       }
    }
}