package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
    private Percolation p;
    private int repeat;
    private int[] totalOpen;
    private int size;
    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if(N <= 0 || T <= 0) {
            throw new IllegalArgumentException("N and T should be greater than 0");
        }
        p = pf.make(N);
        repeat = T;
        totalOpen = new int[T]; // arr of numbersOfOpenSites when percolates
        size = N*N;
        while(repeat > 0) {
            while(!p.percolates()) {
                int row = StdRandom.uniform(0, N);
                int col = StdRandom.uniform(0, N);
                p.open(row, col);
            }
            totalOpen[repeat - 1] = p.numberOfOpenSites();
            repeat -= 1;
            p = pf.make(N);
        }

    }

     //sample mean of percolation threshold
    public double mean(){
        int sum = 0;
        for(int i = 0; i < totalOpen.length; i++) {
            sum += totalOpen[i];
        }
        return ((double) sum/totalOpen.length) / size;

    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        double[] fractionOpen = new double[totalOpen.length];
        for(int i = 0; i < totalOpen.length; i++) {
            fractionOpen[i] = (double)totalOpen[i]/size;
        }
        return StdStats.stddev(fractionOpen);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - stddev();
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + stddev();
    }

    public static void main(String[] args) {
        PercolationFactory pf = new PercolationFactory();
        Stopwatch timer1 = new Stopwatch();
        PercolationStats ps = new PercolationStats(10, 100, pf);
        System.out.println("N = 10: " + timer1.elapsedTime());

        Stopwatch timer2 = new Stopwatch();
        PercolationStats ps2 = new PercolationStats(20, 100, pf);
        System.out.println("N = 20: " + timer2.elapsedTime());

//        System.out.println(ps.mean());
//        System.out.println(ps.stddev());
//        System.out.println(ps.confidenceLow());
//        System.out.println(ps.confidenceHigh());


    }
}
