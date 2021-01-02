public class IntList {
	public int first;
	public IntList rest;

	public IntList(int f, IntList r){
		first = f;
		rest = r;
	}

	public int size(){
		if(rest == null){
			return 1;
		}
		return 1 + this.rest.size();

	}
	public int iterativeSize(){
		IntList p = this;
		int count = 0;
		while(p != null){
			p = p.rest;
			count++;
		}
		return count;
	}
	// public int get(int i){
	// 	IntList p = this;
	// 	int count = 0;
	// 	if(i == 0) {
	// 		return p.first;
	// 	}
	// 	while(count < i){
	// 		p = p.rest;
	// 		count++;
	// 	}
	// 	return p.first;
	// }
	public int recursiveGet(int i){
		if(i == 0) {
			return first;
		}
		return rest.recursiveGet(i-1);

	}
	 public static IntList incrList(IntList L, int x) {
        if(L == null) return null;
        else {
            return new IntList(L.first + x, incrList(L.rest, x));
        }
        // return L;        
    }
    public static IntList dincrList(IntList L, int x) {
        if (L == null){
            return null;
        }
        L.first += x;
        L.rest = dincrList(L.rest, x);

        return L;
    }

	public static void main(String[] args){
		IntList L = new IntList(15, null);
		L = new IntList(10, L);
		L = new IntList(5, L);

		// System.out.println(L.size());
		// System.out.println(L.iterativeSize());
		// System.out.println(L.recursiveGet(1));
		System.out.println(L.incrList(L, 3));

	}
}