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
        List<Integer> bstSize = new ArrayList<>();
        List<Double> bstDepth = new ArrayList<>();
        BST<Integer> bst = new BST<>();
        RandomGenerator r = new RandomGenerator();
        for(int i = 0; i < 5000; i += 1) {
            n.add(i);
            avgdepth.add(ExperimentHelper.optimalAverageDepth(i));
        }
        while(bst.size() < 5000) {
            bst.add(r.getRandomInt(10000));
            bstSize.add(bst.size());
            bstDepth.add(bst.averageDepth());
        }

        XYChart chart = new XYChartBuilder().width(1200).height(600).xAxisTitle("number of items").yAxisTitle("avg depth").build();
        chart.addSeries("Optimal BST avg path depth", n, avgdepth);
        chart.addSeries("Random BST avg path depth", bstSize, bstDepth);

        new SwingWrapper(chart).displayChart();

    }

    public static void experiment2() {
        BST<Integer> bst = new BST<>();
        List<Double> bstDepth = new ArrayList<>();
        List<Integer> operations = new ArrayList<>();
        Random r = new Random();
        int intialSize = 5000;
        int M = 10;
        for(int i = 0; i < intialSize; i += 1) {
            bst.add(r.nextInt());
        }
        bstDepth.add(bst.averageDepth());
        operations.add(0);
        for(int i = 0; i < M; i += 1) {
            ExperimentHelper.randomInsertAndDelete(bst);
            bstDepth.add(bst.averageDepth());
            operations.add(i + 1);
        }
        XYChart chart = new XYChartBuilder().width(1200).height(600).xAxisTitle("number of operations").yAxisTitle("BST avg depth").build();
        chart.addSeries("Random delete and insert BST", operations, bstDepth);
        new SwingWrapper(chart).displayChart();
    }

    public static void experiment3() {
    }

    public static void main(String[] args) {
        experiment1();
//        experiment2();
    }
}
