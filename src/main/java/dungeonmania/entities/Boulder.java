package dungeonmania.entities;

import dungeonmania.entities.enemies.Spider;
import dungeonmania.map.GameMap;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Boulder extends Entity {

    public Boulder(Position position) {
        super(position.asLayer(Entity.CHARACTER_LAYER));
    }

    @Override
    public boolean canMoveOnto(GameMap map, Entity entity) {
        if (entity instanceof Spider)
            return false;
        if (entity instanceof Player && canPush(map, entity.getFacing()))
            return true;
        return false;
    }

    @Override
    public void onOverlap(GameMap map, Entity entity) {
        if (entity instanceof Player) {
            map.moveTo(this, entity.getFacing());
        }
    }

    private boolean canPush(GameMap map, Direction direction) {
        Position newPosition = Position.translateBy(this.getPosition(), direction);
        return map.getEntities(newPosition).stream().allMatch(e -> e.canMoveOnto(map, this));
    }

    @Override
    public String getEntityField() {
        return "boulder";
    }
}
