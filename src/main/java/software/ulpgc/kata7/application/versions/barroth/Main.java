package software.ulpgc.kata7.application.versions.barroth;

import io.javalin.Javalin;
import io.javalin.http.Context;
import software.ulpgc.kata7.application.io.HistogramAdapter;
import software.ulpgc.kata7.application.io.JsonParser;
import software.ulpgc.kata7.application.io.RemoteStore;
import software.ulpgc.kata7.architecture.model.Monster;
import software.ulpgc.kata7.architecture.tasks.HistogramBuilder;
import software.ulpgc.kata7.architecture.viewModel.Histogram;

import java.util.Optional;
import java.util.stream.Stream;

public class Main {
    static void main() {
        Javalin app = Javalin.create();
        app.get("/monsters", Main::monsters);
        app.get("/monster/{name}", Main::monster);
        app.get("/histogram", Main::histogram);
        app.start();
    }

    private static void histogram(Context context) {
        context.json(HistogramAdapter.adapt(getHistogram()));
    }

    private static Histogram<String> getHistogram() {
        return HistogramBuilder.with(getMonsters())
                .setTitle("Monsters per game")
                .setX("Games")
                .setY("Count")
                .setLegend("Monsters")
                .build(m -> m.games().stream());
    }

    private static void monster(Context context) {
        Optional<Monster> any = getMonsters().filter(m -> m.name().equals(context.pathParam("name"))).findAny();
        any.ifPresentOrElse(context::json, () -> context.status(404));
    }

    private static void monsters(Context context) {
        context.json(getMonsters().toList());
    }

    private static Stream<Monster> getMonsters() {
        return new RemoteStore(JsonParser::parse).monsters();
    }
}
