package dungeonmania;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import dungeonmania.entities.BattleItem;
import dungeonmania.entities.Door;
import dungeonmania.entities.Entity;
import dungeonmania.entities.collectables.Key;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.ResponseBuilder;
import dungeonmania.util.Direction;
import dungeonmania.util.FileLoader;

public class DungeonManiaController {
    private Game game = null;

    public String getSkin() {
        return "default";
    }

    public String getLocalisation() {
        return "en_US";
    }

    private String workingDirec = System.getProperty("user.dir");
    private static String defaultDirectory = "/src/main/resources/savedGames/";

    /**
     * /dungeons
     */
    public static List<String> dungeons() {
        return FileLoader.listFileNamesInResourceDirectory("dungeons");
    }

    /**
     * /configs
     */
    public static List<String> configs() {
        return FileLoader.listFileNamesInResourceDirectory("configs");
    }

    /**
     * /game/new
     */
    public DungeonResponse newGame(String dungeonName, String configName) throws IllegalArgumentException {
        if (!dungeons().contains(dungeonName)) {
            throw new IllegalArgumentException(dungeonName + " is not a dungeon that exists");
        }

        if (!configs().contains(configName)) {
            throw new IllegalArgumentException(configName + " is not a configuration that exists");
        }

        try {
            GameBuilder builder = new GameBuilder();
            game = builder.setConfigName(configName).setDungeonName(dungeonName).buildGame(true);
            return ResponseBuilder.getDungeonResponse(game);
        } catch (JSONException e) {
            return null;
        }
    }

    /**
     * /game/dungeonResponseModel
     */
    public DungeonResponse getDungeonResponseModel() {
        return null;
    }

    /**
     * /game/tick/item
     */
    public DungeonResponse tick(String itemUsedId) throws IllegalArgumentException, InvalidActionException {
        return ResponseBuilder.getDungeonResponse(game.tick(itemUsedId));
    }

    /**
     * /game/tick/movement
     */
    public DungeonResponse tick(Direction movementDirection) {
        return ResponseBuilder.getDungeonResponse(game.tick(movementDirection));
    }

    /**
     * /game/build
     */
    public DungeonResponse build(String buildable) throws IllegalArgumentException, InvalidActionException {
        List<String> validBuildables = List.of("bow", "shield", "midnight_armour", "sceptre");
        if (!validBuildables.contains(buildable)) {
            throw new IllegalArgumentException("Only bow, shield, midnight_armour and sceptre can be built");
        }

        return ResponseBuilder.getDungeonResponse(game.build(buildable));
    }

    /**
     * /game/interact
     */
    public DungeonResponse interact(String entityId) throws IllegalArgumentException, InvalidActionException {
        return ResponseBuilder.getDungeonResponse(game.interact(entityId));
    }

    /**
     * /game/save
     * 
     * @throws IOException
     */
    public DungeonResponse saveGame(String name) throws IllegalArgumentException {
        if (FileLoader.listFileNamesInResourceDirectory("savedGames").contains(name)) {
            throw new IllegalArgumentException(name + " already exists");
        }
        // Entities
        JSONObject save = new JSONObject();
        JSONArray entitiesOnMap = new JSONArray();
        List<Entity> entities = game.getMap().getEntities();
        for (Entity e : entities) {
            JSONObject eJSON = new JSONObject();
            eJSON.put("type", e.getEntityField());
            eJSON.put("x", e.getPosition().getX());
            eJSON.put("y", e.getPosition().getY());
            if (e instanceof Key) {
                Key k = (Key) e;
                eJSON.put("key", k.getnumber());
            } else if (e instanceof Door) {
                Door d = (Door) e;
                eJSON.put("key", d.getKey());
            }
            entitiesOnMap.put(eJSON);
        }
        save.put("entities", entitiesOnMap);
        save.put("config", game.getConfigName());
        // Goals
        JSONObject goals = game.getGoals().goalsToConfig();
        save.put("goal-condition", goals);

        // Inventory Items
        JSONArray items = new JSONArray();
        List<Entity> inventoryItems = game.getPlayer().getInventory().getEntities();
        for (Entity e : inventoryItems) {
            JSONObject iJSON = new JSONObject();
            iJSON.put("type", e.getEntityField());
            if (e instanceof Key) {
                Key k = (Key) e;
                iJSON.put("key", k.getnumber());
            }
            if (e instanceof BattleItem) {
                BattleItem i = (BattleItem) e;
                iJSON.put("durability", i.getDurability());
            }
            items.put(iJSON);
        }
        save.put("inventory_items", items);

        String path = String.format("%s%s%s.json", workingDirec, defaultDirectory, name);
        File newFile = new File(path);
        FileWriter file;
        try {
            newFile.createNewFile();
        } catch (IOException e2) {
            // e2.printStackTrace();
            System.out.println("Failed to create");
            return null;
        }
        try {
            file = new FileWriter(path);
            file.write(save.toString());
            System.out.println("Sucessfully copied to JSON file");
            System.out.println("JSON object: " + save);
            file.close();
            return ResponseBuilder.getDungeonResponse(game);
        } catch (IOException e1) {
            // e1.printStackTrace();
            System.out.println("Failed to write");
            return null;
        }
    }

    /**
     * /game/load
     */
    public DungeonResponse loadGame(String name) throws IllegalArgumentException {
        if (!FileLoader.listFileNamesInResourceDirectory("savedGames").contains(name)) {
            throw new IllegalArgumentException(name + " doesn't exist");
        }

        String path = String.format("%s%s%s.json", workingDirec, defaultDirectory, name);
        try {
            String jsonText = Files.readString(Path.of(path));
            JSONObject contents = new JSONObject(jsonText);
            String configName = (String) contents.get("config");
            GameBuilder builder = new GameBuilder();

            // Call method inside builder which passes in inventoryItems and Game.
            JSONArray inventoryItems = (JSONArray) contents.get("inventory_items");

            game = builder.setConfigName(configName).setDungeonName(name).buildGame(false);
            return ResponseBuilder.getDungeonResponse(game);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * /games/all
     */
    public List<String> allGames() {
        return FileLoader.listFileNamesInResourceDirectory("savedGames");
    }

    /**
     * /game/new/generate
     */
    public DungeonResponse generateDungeon(
            int xStart, int yStart, int xEnd, int yEnd, String configName) throws IllegalArgumentException {
        return null;
    }

    /**
     * /game/rewind
     */
    public DungeonResponse rewind(int ticks) throws IllegalArgumentException {
        return null;
    }

}
