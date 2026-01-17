package software.ulpgc.kata6.application.view;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import software.ulpgc.kata6.architecture.viewModel.Histogram;

import javax.swing.*;

public class MainFrame extends JFrame {
    
    public static MainFrame create(){
        return new MainFrame();
    }
    
    private MainFrame(){
        this.setTitle("Monster char");
        this.setSize(1000, 800);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public <T> MainFrame display(Histogram<T> histogram){
        this.getContentPane().add(chartPanelOf(histogram));
        return this;
    }

    private <T> ChartPanel chartPanelOf(Histogram<T> histogram) {
        return new ChartPanel(chartOf(histogram));
    }

    private <T> JFreeChart chartOf(Histogram<T> histogram) {
        JFreeChart chart = ChartFactory.createBarChart(
                histogram.title(),
                histogram.x(),
                histogram.y(),
                datasetOf(histogram)
        );
        chart.getCategoryPlot().getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_90);
        return chart;
    }

    private <T> CategoryDataset datasetOf(Histogram<T> histogram) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        histogram.forEach(bin -> dataset.addValue(histogram.count(bin), histogram.legend(), bin.toString()));
        return dataset;
    }
}
