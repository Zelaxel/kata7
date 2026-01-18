package software.ulpgc.kata7.application.io;

import org.json.JSONArray;
import org.json.JSONObject;
import software.ulpgc.kata7.architecture.io.Store;
import software.ulpgc.kata7.architecture.model.Monster;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public record RemoteStore(Function<JSONObject, Monster> parser) implements Store {
    @Override
    public Stream<Monster> monsters() {
        try {
            return loadFrom(new URL("https://raw.githubusercontent.com/CrimsonNynja/monster-hunter-DB/refs/heads/master/monsters.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Stream<Monster> loadFrom(URL url) throws IOException {
        try(InputStream inputStream = url.openStream()) {
            return loadFrom(toJson(inputStream));
        }
    }

    private Stream<Monster> loadFrom(JSONObject json) {
        return toJsonList(json.getJSONArray("monsters")).stream().map(parser);
    }

    private List<JSONObject> toJsonList(JSONArray jsonArray) {
        List<JSONObject> jsonObjects = new ArrayList<>();
        jsonArray.forEach(j -> jsonObjects.add((JSONObject) j));
        return jsonObjects;
    }

    private JSONObject toJson(InputStream inputStream) throws IOException {
        return new JSONObject(new String(inputStream.readAllBytes()));
    }
}
