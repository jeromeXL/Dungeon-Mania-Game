package dungeonmania.entities.collectables;

import dungeonmania.entities.inventory.InventoryItem;
import dungeonmania.util.Position;

public class Treasure extends Collectables implements InventoryItem, TreasureInterface {
    public Treasure(Position position) {
        super(position);
    }

    public boolean bribable() {
        return true;
    }

    public boolean retains() {
        return false;
    }

    @Override
    public String getEntityField() {
        return "treasure";
    }
}
