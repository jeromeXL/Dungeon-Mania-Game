package dungeonmania.entities.collectables;

import java.util.List;

import dungeonmania.Game;
import dungeonmania.battles.BattleStatistics;
import dungeonmania.entities.BattleItem;
import dungeonmania.entities.Entity;
import dungeonmania.entities.enemies.ZombieToastSpawner;
import dungeonmania.entities.inventory.InventoryItem;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class Sword extends Collectables implements InventoryItem, BattleItem, Useable {
    public static final double DEFAULT_ATTACK = 1;
    public static final double DEFAULT_ATTACK_SCALE_FACTOR = 1;
    public static final int DEFAULT_DURABILITY = 5;
    public static final double DEFAULT_DEFENCE = 0;
    public static final double DEFAULT_DEFENCE_SCALE_FACTOR = 1;

    private int durability;
    private double attack;

    public Sword(Position position, double attack, int durability) {
        super(position);
        this.attack = attack;
        this.durability = durability;
    }

    @Override
    public void use(Game game) {
        durability--;
        if (durability <= 0) {
            game.getPlayer().remove(this);
        }
    }

    @Override
    public BattleStatistics applyBuff(BattleStatistics origin) {
        return BattleStatistics.applyBuff(origin, new BattleStatistics(
                0,
                attack,
                0,
                1,
                1));
    }

    @Override
    public int getDurability() {
        return durability;
    }

    // When a sword is used, look at cardinally adjacent positions, and destroy any
    // spawners.
    public void destroySpawners(GameMap map, Position p) {
        List<Position> adjacentPositions = p.getCardinallyAdjacentPositions();
        for (Position pos : adjacentPositions) {
            List<Entity> eAtPos = map.getEntities(pos);
            for (Entity e : eAtPos) {
                if (e instanceof ZombieToastSpawner) {
                    map.destroyEntity(e);
                }
            }
        }
    }
}
