package software.ulpgc.kata6.application.io;

import software.ulpgc.kata6.application.pojos.HistogramPojo;
import software.ulpgc.kata6.architecture.viewModel.Histogram;

public class HistogramAdapter {
    public static <T> HistogramPojo<T> adapt(Histogram<T> histogram){
        return new HistogramPojo<>(histogram.title(), histogram.x(), histogram.y(), histogram.legend(), histogram.map());
    }
}
