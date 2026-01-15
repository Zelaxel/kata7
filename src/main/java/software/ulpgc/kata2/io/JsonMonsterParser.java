package software.ulpgc.kata2.io;

import org.json.JSONArray;
import org.json.JSONObject;
import software.ulpgc.kata2.model.Monster;

import java.util.ArrayList;
import java.util.List;

public class JsonMonsterParser implements MonsterParser<JSONObject> {
    @Override
    public Monster parse(JSONObject input) {
        return new Monster(
                input.getString("name"),
                toType(input.getString("type")),
                input.getBoolean("isLarge"),
                toGames(input.getJSONArray("games"))
        );
    }

    private List<String> toGames(JSONArray jsonArray) {
        List<JSONObject> jsonObjects = new ArrayList<>();
        jsonArray.forEach(j -> jsonObjects.add((JSONObject) j));
        return jsonObjects.stream().map(j -> j.getString("game")).toList();
    }

    private Monster.Type toType(String type) {
        return Monster.Type.valueOf(type.replace(" ", "_").replace("???", "unknown"));
    }
}
