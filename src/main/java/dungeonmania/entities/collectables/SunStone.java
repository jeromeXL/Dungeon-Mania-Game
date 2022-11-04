package dungeonmania.entities.collectables;

import dungeonmania.util.Position;

public class SunStone extends Collectables {
    public SunStone(Position position) {
        super(position);
    }

    public boolean bribable() {
        return false;
    }

    public boolean retains() {
        return true;
    }
}
