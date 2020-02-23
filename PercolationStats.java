import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final double[] numberofopensite;
    private final double trial;
    private final double numbersquare;


    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n < 1 || trials < 1) {
            throw new IllegalArgumentException("out of bound");
        }
        numberofopensite = new double[trials];
        trial = trials;
        numbersquare = n * n;

        for (int i = 0; i < trials; i++) {
            int count = 0;
            Percolation mytest = new Percolation(n);
            for (int j = 0; j < n * n * n * n; j++) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                if (!mytest.isOpen(row, col)) {
                    mytest.open(row, col);
                    count++;
                }
                if (mytest.percolates()) {
                    break;
                }
            }

            numberofopensite[i] = count;
        }
    }


    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(numberofopensite) / (numbersquare);

    }


    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(numberofopensite) / (numbersquare);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(trial);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(trial);
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = StdIn.readInt();
        int trials = StdIn.readInt();
        if (n < 1 || trials < 1) {
            throw new IllegalArgumentException("out of bound");
        }
        PercolationStats test = new PercolationStats(n, trials);
        System.out.println(test.mean());
        System.out.println(test.stddev());
        System.out.println("[" + test.confidenceLo() + ", " + test.confidenceHi() + "]");

    }
}
