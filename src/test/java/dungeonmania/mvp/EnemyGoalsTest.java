package dungeonmania.mvp;

import dungeonmania.DungeonManiaController;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.BattleResponse;
import dungeonmania.response.models.RoundResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyGoalsTest {
    @Test
    @Tag("15-1")
    @DisplayName("Test achieving a basic enemies goal with only one enemy and no spawners")
    public void KillOneEnemy() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_enemyGoalsTest_KillOneEnemy", "c_enemyGoalsTest_KillOneEnemy");

        // assert goal not met
        assertTrue(TestUtils.getGoals(res).contains(":enemies"));

        // move player to right to fight Spider
        // The player has 100 damage compared to spiders 5 health, so the player should
        // win
        res = dmc.tick(Direction.RIGHT);

        // assert goal met
        assertEquals("", TestUtils.getGoals(res));
    }

    @Test
    @Tag("15-2")
    @DisplayName("Test achieving a basic enemies goal by killing one enemy and destroying the zombie spawner")
    public void KillAnEnemyThenDestroySpawner() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_enemyGoalsTest_KillAnEnemyThenDestroySpawner",
                "c_enemyGoalsTest_KillAnEnemyThenDestroySpawner");

        // assert goal not met
        assertTrue(TestUtils.getGoals(res).contains(":enemies"));

        // move player to right to fight Spider
        // The player has 100 damage compared to spiders 5 health, so the player should
        // win
        res = dmc.tick(Direction.RIGHT);

        // assert goal not met
        assertTrue(TestUtils.getGoals(res).contains(":enemies"));

        // Move right 3 times and destroy spawner, picking up a sword on the way
        for (int i = 0; i < 3; i++) {
            res = dmc.tick(Direction.RIGHT);
        }

        // assert goal met
        assertEquals("", TestUtils.getGoals(res));
    }

    @Test
    @Tag("15-3")
    @DisplayName("Killing an enemy and then use exit to achieve AND goal")
    public void KillAnEnemyThenExitGoal() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_enemyGoalsTest_KillAnEnemyThenExitGoal",
                "c_enemyGoalsTest_KillAnEnemyThenExitGoal");

        // assert goal not met
        assertTrue(TestUtils.getGoals(res).contains(":enemies"));
        assertTrue(TestUtils.getGoals(res).contains(":exit"));

        // move player to right to fight Spider
        // The player has 100 damage compared to spiders 5 health, so the player should
        // win
        res = dmc.tick(Direction.RIGHT);

        // assert enemy goal achieved but exit goal not achieved.
        assertFalse(TestUtils.getGoals(res).contains(":enemies"));
        assertTrue(TestUtils.getGoals(res).contains(":exit"));

        // Move right 3 times and move to exit which is at (5,3)
        for (int i = 0; i < 3; i++) {
            res = dmc.tick(Direction.RIGHT);
        }

        // assert goal met
        assertEquals("", TestUtils.getGoals(res));
    }

    @Test
    @Tag("15-4")
    @DisplayName("Complex goal of Exit AND (enemies or treasure goal)")
    public void ComplexEnemyGoalAndOr() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_enemyGoalsTest_ComplexEnemyGoalAndOr",
                "c_enemyGoalsTest_ComplexEnemyGoalAndOr");
        // assert goal not met
        assertTrue(TestUtils.getGoals(res).contains(":exit"));
        assertTrue(TestUtils.getGoals(res).contains(":enemies"));
        assertTrue(TestUtils.getGoals(res).contains(":treasure"));
    }
}
