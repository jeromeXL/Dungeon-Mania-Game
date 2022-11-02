package dungeonmania;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import dungeonmania.entities.Entity;
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
        // Simple persistence
        JSONObject save = new JSONObject();
        JSONArray arr = new JSONArray();
        List<Entity> entities = game.getMap().getEntities();
        for (Entity e : entities) {
            JSONObject eJSON = new JSONObject();
            eJSON.put("type", e.getClass().getSimpleName().toLowerCase());
            eJSON.put("x", e.getPosition().getX());
            eJSON.put("y", e.getPosition().getY());
            arr.put(eJSON);
        }
        save.put("entities", arr);
        save.put("config", game.getConfigName());
        // Change this
        JSONObject goals = new JSONObject();
        goals.put("goal", "enemies");
        save.put("goal-condition", goals);
        String path = String.format("%s%s%s.json", workingDirec, defaultDirectory, name);
        File newFile = new File(path);
        FileWriter file;
        try {
            newFile.createNewFile();
        } catch (IOException e2) {
            // e2.printStackTrace();
            System.out.println("Failed to create");
        }
        try {
            file = new FileWriter(path);
            file.write(save.toString());
            System.out.println("Sucessfully copied to JSON file");
            System.out.println("JSON object: " + save);
            file.close();
        } catch (IOException e1) {
            // e1.printStackTrace();
            System.out.println("Failed to write");
        }
        return null;
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
            FileReader fileReader = new FileReader(path);
            Object obj = JsonParser.parseReader(fileReader);
            JSONObject contents = (JSONObject) obj;
            String configName = (String) contents.get("config");
            GameBuilder builder = new GameBuilder();
            game = builder.setConfigName(configName).setDungeonName(name).buildGame(false);
            return ResponseBuilder.getDungeonResponse(game);
        } catch (FileNotFoundException e) {
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
