/**
 * Created by hug.
 */
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class Experiments {

    /* insert 5000 random items into a BST.
    Make a plot of the average depth of your BST vs. the number of items.
     */
    public static void experiment1() {
        List<Double> avgdepth = new ArrayList<>();
        List<Integer> n = new ArrayList<>();
        List<Double> bstDepth = new ArrayList<>();
        BST<Integer> bst = new BST<>();
        for(int i = 0; i < 5000; i += 1) {
            Random r = new Random();
            bst.add(r.nextInt());
            n.add(i);
            avgdepth.add(ExperimentHelper.optimalAverageDepth(i));
            bstDepth.add(bst.averageDepth());
        }

        XYChart chart = new XYChartBuilder().width(1200).height(600).xAxisTitle("number of items").yAxisTitle("avg depth").build();
        chart.addSeries("Optimal BST avg path depth", n, avgdepth);
        chart.addSeries("Random BST avg path depth", n, bstDepth);

        new SwingWrapper(chart).displayChart();

    }

    public static void experiment2() {
    }

    public static void experiment3() {
    }

    public static void main(String[] args) {
        experiment1();

    }
}
