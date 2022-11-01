package dungeonmania.mvp;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.response.models.DungeonResponse;
// import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class PersistenceTest {
    private Position playerPos;
    private Position spider1Pos;
    private Position spider2Pos;

    @Test
    @Tag("22-1")
    @DisplayName("Test saving game")
    public void saving() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_PersistenceTest_basicPosition",
                "c_PersistenceTest_basicPosition");
        playerPos = TestUtils.getEntities(res, "player").get(0).getPosition();
        spider1Pos = TestUtils.getEntities(res, "spider").get(0).getPosition();
        spider2Pos = TestUtils.getEntities(res, "spider").get(1).getPosition();
        assertDoesNotThrow(() -> dmc.saveGame("PersistenceTest_saving"));
    }

    // @Test
    // @Tag("22-2")
    // @DisplayName("Test saving game")
    // public void openSaved() {
    // DungeonManiaController dmc = new DungeonManiaController();
    // DungeonResponse res = dmc.loadGame("PersistenceTest_saving");
    // assert(TestUtils.getEntities(res,
    // "player").get(0).getPosition().equals(playerPos));
    // assert(TestUtils.getEntities(res,
    // "spider").get(0).getPosition().equals(spider1Pos));
    // assert(TestUtils.getEntities(res,
    // "spider").get(1).getPosition().equals(spider2Pos));
    // }

    // @Test
    // @Tag("22-3")
    // @DisplayName("Test saving game")
    // public void moveThenSaveAndReopen() {
    // DungeonManiaController dmc = new DungeonManiaController();
    // DungeonResponse res = dmc.newGame("d_PersistenceTest_basicPosition",
    // "c_PersistenceTest_basicPosition");
    // res = dmc.tick(Direction.RIGHT);
    // res = dmc.tick(Direction.UP);
    // res = dmc.tick(Direction.UP);
    // Position player = TestUtils.getEntities(res, "player").get(0).getPosition();
    // Position spider1 = TestUtils.getEntities(res, "spider").get(0).getPosition();
    // Position spider2 = TestUtils.getEntities(res, "spider").get(1).getPosition();
    // assertDoesNotThrow(() -> dmc.saveGame("PersistenceTest_saving2"));

    // res = dmc.loadGame("PersistenceTest_saving2");
    // assert(player.equals(TestUtils.getEntities(res,
    // "player").get(0).getPosition()));
    // assert(spider1.equals(TestUtils.getEntities(res,
    // "spider").get(0).getPosition()));
    // assert(spider2.equals(TestUtils.getEntities(res,
    // "spider").get(1).getPosition()));
    // }

    // @Test
    // @Tag("22-4")
    // @DisplayName("Test saving, loading then achieving goal")
    // public void saveLoadThenAchieveGoal() {
    // DungeonManiaController dmc = new DungeonManiaController();
    // DungeonResponse res =
    // dmc.newGame("d_PersistenceTest_saveLoadThenAchieveGoal",
    // "c_PersistenceTest_saveLoadThenAchieveGoal");
    // assertDoesNotThrow(() -> dmc.saveGame("saveLoadThenAchieveGoal"));
    // res = dmc.loadGame("saveLoadThenAchieveGoal");
    // res = dmc.tick(Direction.RIGHT);
    // assertEquals("", TestUtils.getGoals(res));
    // }
}
