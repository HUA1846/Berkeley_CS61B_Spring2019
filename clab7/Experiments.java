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
        List<Double> bstDepth = new ArrayList<>(); // y-axis
        List<Integer> operations = new ArrayList<>(); // x-axis
        RandomGenerator r = new RandomGenerator();
        int intialSize = 5000;
        int M = 5000;
        /* Initialize a tree by randomly inserting N items. Record the average depth observed as the starting depth
         */
        while(bst.size() < intialSize) {
            int rand = r.getRandomInt(10000);
            if(!bst.contains(rand)) {
                bst.add(rand);
            }
        }
        bstDepth.add(bst.averageDepth());
        operations.add(0);
        /*  1. Randomly delete an item using asymmetric Hibbard deletion.
            2. Randomly insert a new item.
            3. Record the average depth of the tree.
            Repeat M operations
         */
        for(int i = 0; i < M; i += 1) {
            ExperimentHelper.randomDelete(bst);
            ExperimentHelper.randomInsert(bst, 20000);
            bstDepth.add(bst.averageDepth());
            operations.add(i + 1);
        }
        XYChart chart = new XYChartBuilder().width(1200).height(600).xAxisTitle("number of operations").yAxisTitle("BST avg depth").build();
        chart.addSeries("BST asymmetric Hibbard deletion", operations, bstDepth);
        new SwingWrapper(chart).displayChart();
    }

    public static void experiment3() {
        BST<Integer> bst = new BST<>();
        List<Double> bstDepth = new ArrayList<>(); // y-axis
        List<Integer> operations = new ArrayList<>(); // x-axis
        RandomGenerator r = new RandomGenerator();
        int intialSize = 5000;
        int M = 5000;
        /* Initialize a tree by randomly inserting N items. Record the average depth observed as the starting depth
         */
        while(bst.size() < intialSize) {
            int rand = r.getRandomInt(10000);
            if(!bst.contains(rand)) {
                bst.add(rand);
            }
        }
        bstDepth.add(bst.averageDepth());
        operations.add(0);
        /*  1. Randomly delete an item using symmetric deletion (i.e. randomly picking between successor and predecessor)
            2. Randomly insert a new item.
            3. Record the average depth of the tree.
            Repeat M operations
         */
        for(int i = 0; i < M; i += 1) {
            ExperimentHelper.deleteTakingRandom(bst);
            ExperimentHelper.randomInsert(bst, 10000);
            bstDepth.add(bst.averageDepth());
            operations.add(i + 1);
        }
        XYChart chart = new XYChartBuilder().width(1200).height(600).xAxisTitle("number of operations").yAxisTitle("BST avg depth").build();
        chart.addSeries("BST symmetric deletion", operations, bstDepth);
        new SwingWrapper(chart).displayChart();
    }

    public static void main(String[] args) {
//        experiment1();
//        experiment2();
        experiment3();
    }
}
