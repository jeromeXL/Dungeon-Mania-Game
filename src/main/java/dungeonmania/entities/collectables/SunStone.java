package dungeonmania.entities.collectables;

import dungeonmania.entities.inventory.InventoryItem;
import dungeonmania.util.Position;

public class SunStone extends Collectables implements InventoryItem, TreasureInterface, KeyInterface {
    public SunStone(Position position) {
        super(position);
    }

    public boolean bribable() {
        return false;
    }

    public boolean retains() {
        return true;
    }

    @Override
    public String getEntityField() {
        return "sun_stone";
    }
}
