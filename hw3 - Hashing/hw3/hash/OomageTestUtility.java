package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        int bucketNum = 0;
        int[] hs = new int[M];
        int N = oomages.size();
        for(Oomage o : oomages) {
            bucketNum = (o.hashCode() & 0x7FFFFFFF) % M;
            hs[bucketNum] += 1;
        }
        for(int i = 0; i < M; i ++) {
            if(hs[i] < N/50 || hs[i] > N/2.5) return false;
        }

        return true;
    }
}
