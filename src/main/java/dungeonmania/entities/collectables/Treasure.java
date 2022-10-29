package dungeonmania.entities.collectables;

import dungeonmania.entities.inventory.InventoryItem;
import dungeonmania.util.Position;

public class Treasure extends Collectables implements InventoryItem {
    public Treasure(Position position) {
        super(position);
    }

    public boolean bribable() {
        return true;
    }
    
    public boolean retains() {
        return false;
    }

}
