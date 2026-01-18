package software.ulpgc.kata7.application.versions.jagras;

import software.ulpgc.kata7.application.io.JsonParser;
import software.ulpgc.kata7.application.io.RemoteStore;
import software.ulpgc.kata7.application.view.MainFrame;
import software.ulpgc.kata7.architecture.model.Monster;
import software.ulpgc.kata7.architecture.tasks.HistogramBuilder;
import software.ulpgc.kata7.architecture.viewModel.Histogram;

import java.util.stream.Stream;

public class Main {
    static void main() {
        MainFrame
                .create()
                .display(getHistogram())
                .setVisible(true);
    }

    private static Histogram<String> getHistogram() {
        return HistogramBuilder
                .with(getMonsters())
                .setTitle("Monsters per game")
                .setX("Games")
                .setY("Count")
                .setLegend("Monsters")
                .build(m -> m.games().stream());
    }

    private static Stream<Monster> getMonsters() {
        return new RemoteStore(JsonParser::parse).monsters();
    }
}
