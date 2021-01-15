import java.util.NoSuchElementException;

public class UnionFind {
    // TODO - Add instance variables?
    public int[] arr;
    public int[] sizes;
    private int num;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        num = n;
        arr = new int[num];
        sizes = new int[num];
        for(int i = 0; i < num; i++) {
            arr[i] = -1;
            sizes[i] = 1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if(vertex >= num) {
            throw new NoSuchElementException();
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {

        return sizes[parent(v1)];
    }


    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        if(arr[v1] < 0) {
            return v1;
        }
        return arr[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        if(find(v1) == find(v2)) {
            return true;
        }
        return false;
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
       validate(v1);
       validate(v2);

       if(sizeOf(v1) < sizeOf(v2)) {
           sizes[parent(v2)] += sizes[parent(v1)];
           arr[parent(v1)] = parent(v2);

       } else {
           sizes[parent(v1)] += sizes[parent(v2)];
           arr[parent(v2)] = parent(v1);
       }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        validate(vertex);
        int root = vertex;
        int layer = 0;
        while(arr[root] != -1) {
            root = parent(root);
            layer += 1;
        }
        /* path compression. Runs only > 1 layer from root */
        if(layer > 1) {
            for(int i = 0; i < num; i++) {
                if(arr[i] == arr[vertex]) {
                    arr[i] = root;
                }
            }
        }

        return root;
    }


/*    No path compression
    public int find(int vertex) {
        int root;
        if(arr[vertex] == -1) {
            root = vertex;
        } else {
            root = find(parent(vertex));
        }

        return root;
    } */

}
