package software.ulpgc.kata4.application;

import software.ulpgc.kata4.architecture.model.Monster;
import software.ulpgc.kata4.architecture.tasks.HistogramBuilder;
import software.ulpgc.kata4.architecture.viewModel.Histogram;

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
        return new RemoteMonsterStore(JsonMonsterParser::parse).monsters();
    }
}
