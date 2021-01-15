public class BubbleGrid extends UnionFind {
    private int[][] grid;


    /* Create new BubbleGrid with bubble/space locations specified by grid.
     * Grid is composed of only 1's and 0's, where 1's denote a bubble, and
     * 0's denote a space. */
    public BubbleGrid(int[][] grid) {
        super(grid[0].length * grid.length);
        int row = grid.length;
        int col = grid[0].length;
        this.grid = grid;
        int count = 0;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(grid[i][j] == 1) {
                    arr[count] = - 1;
                } else {
                    arr[count] = -2;
                }
                count += 1;
            }
        }

        /* union bubbles vertically */
        for(int i = 1; i < row; i++) {
            for(int j = i * col; j < (i+1)*col; j++) {
                if(arr[j] == -2) continue;
                if(arr[j] == -1 && arr[j-col] == -1) {
                    union(j-col, j);
                }
            }
        }

        /* union bubbles horizontally */
        for(int i = 1; i < row; i++) {
            for(int j = i*col + 1; j < (i+1)*col; j++) {
                if(arr[j] == -1 && arr[j-1] != -2) {
                    union(j-1, j);
                    sizes[j-1] += 1;
                }
            }
        }

    }

    /* Returns an array whose i-th element is the number of bubbles that
     * fall after the i-th dart is thrown. Assume all elements of darts
     * are unique, valid locations in the grid. Must be non-destructive
     * and have no side-effects to grid. */
    public int[] popBubbles(int[][] darts) {
        int[] res = new int[2];
        int col = grid[0].length;
        if(arr[darts[0][0]*col + darts[0][1]] != -2) {
            res[0] = sizes[darts[0][0]*col + darts[0][1]] - 1; //popped bubbles do not fall
        }
        if(arr[darts[1][0]*col + darts[1][1]] != -2) {
            res[1] = sizes[darts[1][0]*col + darts[1][1]] - 1;
        }

        return res;
    }
}
