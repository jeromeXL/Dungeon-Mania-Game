package dungeonmania.entities.enemies;

import java.util.List;

import dungeonmania.Game;
import dungeonmania.entities.Entity;
import dungeonmania.entities.Interactable;
import dungeonmania.entities.Player;
import dungeonmania.entities.collectables.Treasure;
import dungeonmania.entities.enemies.MovementBehaviour.FollowPlayerMovement;
import dungeonmania.entities.enemies.MovementBehaviour.ShortestPathMovement;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class Mercenary extends Enemy implements Interactable {
    public static final int DEFAULT_BRIBE_AMOUNT = 1;
    public static final int DEFAULT_BRIBE_RADIUS = 1;
    public static final double DEFAULT_ATTACK = 5.0;
    public static final double DEFAULT_HEALTH = 10.0;
    private boolean allied = false;

    private int bribeAmount = Mercenary.DEFAULT_BRIBE_AMOUNT;
    private int bribeRadius = Mercenary.DEFAULT_BRIBE_RADIUS;

    public Mercenary(Position position, double health, double attack, int bribeAmount, int bribeRadius) {
        super(position, health, attack);
        this.bribeAmount = bribeAmount;
        this.bribeRadius = bribeRadius;
        super.changeMovement(new ShortestPathMovement(this));
    }

    @Override
    public boolean isAllied() {
        return allied;
    }

    @Override
    public void setAllied() {
        allied = true;
    }

    @Override
    public void onOverlap(GameMap map, Entity entity) {
        if (isAllied())
            return;
        super.onOverlap(map, entity);
    }

    /**
     * check whether the current merc can be bribed
     *
     * @param player
     * @return
     */
    private boolean canBeBribed(Player player) {
        return bribeRadius >= 0 && player.countEntityOfType(Treasure.class) >= bribeAmount;
    }

    /**
     * bribe the merc
     */
    private void bribe(Player player) {
        for (int i = 0; i < bribeAmount; i++) {
            player.use(Treasure.class);
        }

    }

    @Override
    public void interact(Player player, Game game) {
        setAllied();
        bribe(player);
        isAdjacentToPlayer(game.getMap());
    }

    @Override
    public boolean isInteractable(Player player) {
        return !isAllied() && canBeBribed(player);
    }

    @Override
    public void isAdjacentToPlayer(GameMap map) {
        if (allied) {
            List<Player> p = getCardAdjEntities(Player.class, map, getPosition());
            if (p.size() == 1) {
                this.changeMovement(new FollowPlayerMovement(this, map.getPlayer()));
            }
        }
    }
}
