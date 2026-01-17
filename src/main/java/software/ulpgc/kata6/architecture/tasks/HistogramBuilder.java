package software.ulpgc.kata6.architecture.tasks;

import software.ulpgc.kata6.architecture.model.Monster;
import software.ulpgc.kata6.architecture.viewModel.Histogram;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

public class HistogramBuilder {
    private final Stream<Monster> monsters;
    private final Map<String, String> labels;

    public static HistogramBuilder with(Stream<Monster> monsters){
        if(monsters == null) throw new IllegalArgumentException("Monsters can't be null");
        return new HistogramBuilder(monsters);
    }
    
    private HistogramBuilder(Stream<Monster> monsters) {
        this.monsters = monsters;
        labels = new HashMap<>();
    }

    public <T> Histogram<T> build(Function<Monster, Stream<T>> binarize) {
        Histogram<T> histogram = new Histogram<>(labels);
        monsters.flatMap(binarize).forEach(histogram::put);
        return histogram;
    }
    
    public HistogramBuilder setTitle(String title){
        labels.put("title", title);
        return this;
    }

    public HistogramBuilder setX(String x){
        labels.put("x", x);
        return this;
    }

    public HistogramBuilder setY(String y){
        labels.put("y", y);
        return this;
    }

    public HistogramBuilder setLegend(String legend){
        labels.put("legend", legend);
        return this;
    }
}
