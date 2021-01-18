package hw2;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
        private boolean[][] grid;
        private int open;
        private int length;
        private int vTop;
        private int vBottom;
        private WeightedQuickUnionUF uf;

        // create N-by-N grid, with all sites initially blocked
        public Percolation(int N) {
                grid = new boolean[N][N];

                /* virtual top and virtual bottom sites */
                uf = new WeightedQuickUnionUF(N * N + 2);
                open = 0;
                length = N;
                vTop = N*N;
                vBottom = N*N + 1;

        }

        // open the site (row, col) if it is not open already
        public void open(int row, int col) {
                if(row < 0 || col < 0 || row >= length  || col >= length) {
                        throw new IndexOutOfBoundsException();
                }
                if(!grid[row][col]) {
                  grid[row][col] = true;
                  open += 1;
                } else return;

                openHelper(row, col);
                if(row == 0) {
                   uf.union(xyTo1D(row, col), vTop);
                }
                if(row == length - 1) {
                   uf.union(xyTo1D(row, col), vBottom);
                }

        }
        public int xyTo1D(int row, int col) {
                return row*length + col;
        }
        public void openHelper(int row, int col) {
                int current = xyTo1D(row, col);
                if(col > 0) {
                        int left = xyTo1D(row, col-1);
                        if(grid[row][col-1]) uf.union(left, current);
                }
                if(col < length - 1) {
                        int right = xyTo1D(row, col+1);
                        if(grid[row][col+1]) uf.union(current, right);
                }
                if(row > 0) {
                        int top = xyTo1D(row-1, col);
                        if(grid[row-1][col]) uf.union(top, current);
                }
                if(row < length - 1) {
                        int bottom = xyTo1D(row+1, col);
                        if(grid[row+1][col]) uf.union(current, bottom);
                }

        }

        public boolean isOpen(int row, int col) {
                return grid[row][col];
        }

        public boolean isFull(int row, int col) {
                return uf.connected(vTop, xyTo1D(row, col));

        }

        // must take constant time
        public int numberOfOpenSites() {
                return open;
        }

        public boolean percolates() {
                return uf.connected(vTop, vBottom);
        }

//        public static void main(String[] args) {
//                Percolation p = new Percolation(5);
//        }
}
