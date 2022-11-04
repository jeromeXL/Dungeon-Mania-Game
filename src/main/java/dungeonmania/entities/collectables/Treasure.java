package dungeonmania.entities.collectables;

import dungeonmania.util.Position;

public class Treasure extends Collectables implements TreasureInterface {
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
