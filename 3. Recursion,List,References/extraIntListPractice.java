public class ExtraIntListPractice {
    // public int first;
    // public IntList tail;

    // public IntList(int f, IntList t){
    //     tail = t;
    // }
    // public int size(){
    //     if(tail == null) return 1;
    //     return 1 + this.tail.size();
    // }
    // public int get(int i){
    //     if(i == 0) return first;
    //     return tail.get(i-1);
    // }
    public static IntList incrList(IntList L, int x) {
        if(L == null) return null;
        else {
            return new IntList(L.first + x, incrList(L.rest, x));
        }
        return L;        
    }

    public static IntList dincrList(IntList L, int x) {
        /* Your code here. */
        return L;
    }

    public static void main(String[] args) {
        IntList L = new IntList(5, null);
        L.tail = new IntList(7, null);
        L.tail.tail = new IntList(9, null);

        System.out.println(L.size());
        // System.out.println(L.iterativeSize());

        // Test your answers by uncommenting. Or copy and paste the
        // code for incrList and dincrList into IntList.java and
        // run it in the visualizer.
        System.out.println(L.get(1));
        System.out.println(incrList(L, 3));
        // System.out.println(dincrList(L, 3));        
    }
}
