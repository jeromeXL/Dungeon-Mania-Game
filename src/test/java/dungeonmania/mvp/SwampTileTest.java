package dungeonmania.mvp;

import dungeonmania.DungeonManiaController;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SwampTileTest {
    @Test
    @Tag("21-1")
    @DisplayName("Test the player not affected by swamp tile")
    public void testPlayerUnaffectedBySwamp() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse initDungonRes = dmc.newGame(
                "d_SwampTileTest_testPlayerUnaffected", "c_SwampTileTest_testPlayerUnaffected");
        EntityResponse initPlayer = TestUtils.getPlayer(initDungonRes).get();

        // create the expected result
        EntityResponse expectedPlayer = new EntityResponse(initPlayer.getId(),
                initPlayer.getType(), new Position(2, 1), false);

        // move player right
        DungeonResponse actualDungonRes = dmc.tick(Direction.RIGHT);
        EntityResponse actualPlayer = TestUtils.getPlayer(actualDungonRes).get();

        // assert after movement
        assertTrue(TestUtils.entityResponsesEqual(expectedPlayer, actualPlayer));
    }

    @Test
    @Tag("21-2")
    @DisplayName("Test an enemy mercenary is affected")
    public void EnemyAffected() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame(
                "d_SwampTileTest_enemyAffected", "c_SwampTileTest_testPlayerUnaffected");

        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        // The mercenary gets moves onto the swamp tile
        assertEquals(TestUtils.getEntities(res, "mercenary").get(0).getPosition(),
                TestUtils.getEntities(res, "swamp_tile").get(0).getPosition());
        // Should be stuck on the swamp tile for the next 2 turns
        res = dmc.tick(Direction.RIGHT);
        assertEquals(TestUtils.getEntities(res, "mercenary").get(0).getPosition(),
                TestUtils.getEntities(res, "swamp_tile").get(0).getPosition());

        res = dmc.tick(Direction.RIGHT);
        assertEquals(TestUtils.getEntities(res, "mercenary").get(0).getPosition(),
                TestUtils.getEntities(res, "swamp_tile").get(0).getPosition());

        // Can move off the tile
        res = dmc.tick(Direction.RIGHT);
        assertNotEquals(TestUtils.getEntities(res, "mercenary").get(0).getPosition(),
                TestUtils.getEntities(res, "swamp_tile").get(0).getPosition());
    }

    @Test
    @Tag("21-2")
    @DisplayName("Test an enemy mercenary is affected")
    public void AllyAffected() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame(
                "d_SwampTileTest_enemyAffected", "c_SwampTileTest_testPlayerUnaffected");

        String mercId = TestUtils.getEntitiesStream(res, "mercenary").findFirst().get().getId();
        // Pick up treasure
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "treasure").size());
        // bribe mercenary
        assertDoesNotThrow(() -> dmc.interact(mercId));
        // The allied mercenary gets moves onto the swamp tile
        assertEquals(TestUtils.getEntities(res, "mercenary").get(0).getPosition(),
                TestUtils.getEntities(res, "swamp_tile").get(0).getPosition());
        // Should be stuck on the swamp tile for the next 2 turns
        res = dmc.tick(Direction.RIGHT);
        assertEquals(TestUtils.getEntities(res, "mercenary").get(0).getPosition(),
                TestUtils.getEntities(res, "swamp_tile").get(0).getPosition());

        res = dmc.tick(Direction.RIGHT);
        assertEquals(TestUtils.getEntities(res, "mercenary").get(0).getPosition(),
                TestUtils.getEntities(res, "swamp_tile").get(0).getPosition());

        // Can move off the tile
        res = dmc.tick(Direction.RIGHT);
        assertNotEquals(TestUtils.getEntities(res, "mercenary").get(0).getPosition(),
                TestUtils.getEntities(res, "swamp_tile").get(0).getPosition());
    }
}
