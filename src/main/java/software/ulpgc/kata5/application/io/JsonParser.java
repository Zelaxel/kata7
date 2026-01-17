package software.ulpgc.kata5.application.io;

import org.json.JSONArray;
import org.json.JSONObject;
import software.ulpgc.kata5.architecture.model.Monster;

import java.util.ArrayList;
import java.util.List;

public class JsonParser {
    public static Monster parse(JSONObject input) {
        return new Monster(
                input.getString("name"),
                toType(input.getString("type")),
                input.getBoolean("isLarge"),
                toGames(input.getJSONArray("games"))
        );
    }

    private static List<String> toGames(JSONArray jsonArray) {
        List<JSONObject> jsonObjects = new ArrayList<>();
        jsonArray.forEach(j -> jsonObjects.add((JSONObject) j));
        return jsonObjects.stream().map(j -> j.getString("game")).toList();
    }

    private static Monster.Type toType(String type) {
        return Monster.Type.valueOf(type.replace(" ", "_").replace("???", "unknown"));
    }
}
