import java.util.Random;
/**
 * Created by hug.
 */
public class ExperimentHelper {

    /** Returns the internal path length for an optimum binary search tree of
     *  size N. Examples: the sum of the lengths of the paths to every node.
     *  N = 1, OIPL: 0
     *  N = 2, OIPL: 1
     *  N = 3, OIPL: 2
     *  N = 4, OIPL: 4
     *  N = 5, OIPL: 6
     *  N = 6, OIPL: 8
     *  N = 7, OIPL: 10
     *  N = 8, OIPL: 13
     */
    public static int optimalIPL(int N) {
        int[] OIPL = new int[N + 1];
        for(int i = 1; i < OIPL.length; i ++) {
            if(i == 1) {
                OIPL[i] = 0;
            } else {
                OIPL[i] = (int) Math.floor((Math.log(i) / Math.log(2))) + OIPL[i-1];
            }

        }
        return OIPL[N];
    }

    /** Returns the average depth for nodes in an optimal BST of
     *  size N.
     *  Examples:
     *  N = 1, OAD: 0
     *  N = 5, OAD: 1.2
     *  N = 8, OAD: 1.625
     * @return
     */
    public static double optimalAverageDepth(int N) {
        return (double) optimalIPL(N) / N;
    }

    public static void randomInsert(BST<Integer> bst, int max) {
        RandomGenerator r = new RandomGenerator();
        int rand = r.getRandomInt(max);
        while(bst.contains(rand)) {
            rand = r.getRandomInt(max);
        }
        bst.add(rand);
    }

    public static void randomDelete(BST<Integer> bst) {
            bst.deleteTakingSuccessor(bst.getRandomKey());
    }

    public static void deleteTakingRandom(BST<Integer> bst) {
        bst.deleteTakingRandom(bst.getRandomKey());
    }

    public static void main(String[] args) {
        optimalIPL(8);
    }
}
