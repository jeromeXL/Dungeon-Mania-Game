package dungeonmania.mvp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import dungeonmania.util.Direction;
import dungeonmania.util.Position;
import dungeonmania.DungeonManiaController;
import dungeonmania.response.models.DungeonResponse;

public class LogicalTest {
    @Test
    @Tag("24-1")
    @DisplayName("Test Light Bulb Turns On (OR)")
    public void ORLightBulb() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_LogicalTest_ORLightBulb",
        "c_LogicalTest");

        // Push Boulder Onto Switch
        res = dmc.tick(Direction.RIGHT);

        // Check light bulb is on
        assertEquals(1, TestUtils.getEntities(res, "light_bulb_on").size());
    }

    @Test
    @Tag("24-2")
    @DisplayName("Test Light Bulb Turns On (XOR)")
    public void XORLightBulb() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_LogicalTest_XORLightBulb",
        "c_LogicalTest");

        // Push Boulder Onto Switch
        res = dmc.tick(Direction.RIGHT);

        // Check light bulb is on
        assertEquals(1, TestUtils.getEntities(res, "light_bulb_on").size());
    }
    
    @Test
    @Tag("24-3")
    @DisplayName("Test Light Bulb Turns On (AND)")
    public void ANDLightBulb() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_LogicalTest_ANDLightBulb",
        "c_LogicalTest");

        // Push Boulder Onto Switch
        res = dmc.tick(Direction.RIGHT);

        // Check light bulb is on
        assertEquals(1, TestUtils.getEntities(res, "light_bulb_on").size());
    }

    @Test
    @Tag("24-4")
    @DisplayName("Test Swtich Door Opens (OR)")
    public void ORSwitchDoor() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_LogicalTest_ORSwitchDoor",
        "c_LogicalTest");

        // Push Boulder Onto Switch
        res = dmc.tick(Direction.RIGHT);

        // Move player up to Switch Door
        res = dmc.tick(Direction.UP);

        // Check player is on Switch Door
        assertEquals(new Position(2, 2), getPlayerPos(res));
    }

    @Test
    @Tag("24-5")
    @DisplayName("Test Switch Door Opens (XOR)")
    public void XORSwitchDoor() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_LogicalTest_XORSwitchDoor",
        "c_LogicalTest");

        // Push Boulder Onto Switch
        res = dmc.tick(Direction.RIGHT);

        // Move player up to Switch Door
        res = dmc.tick(Direction.UP);

        // Check player is on Switch Door
        assertEquals(new Position(2, 2), getPlayerPos(res));
    }

    @Test
    @Tag("24-6")
    @DisplayName("Test Switch Door Opens (AND)")
    public void ANDSwitchDoor() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_LogicalTest_ANDSwtichDoor",
        "c_LogicalTest");

        // Push Boulder Onto Switch
        res = dmc.tick(Direction.RIGHT);

        // Move player up to Switch Door
        res = dmc.tick(Direction.UP);

        // Check player is on Switch Door
        assertEquals(new Position(2, 2), getPlayerPos(res));
    }

    private Position getPlayerPos(DungeonResponse res) {
        return TestUtils.getEntities(res, "player").get(0).getPosition();
    }
}
