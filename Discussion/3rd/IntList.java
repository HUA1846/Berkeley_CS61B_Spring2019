public class IntList {
	public int first;
	public IntList rest;

	public IntList(int f, IntList r) {
		first = f;
		rest = r;
	}

	public void skippify() {
		IntList p = this;
		int n = 1;
		while (p != null) {
			IntList next = p.rest;
			for(int i = 0; i < n; i++) {
				if( next == null) {
					break;
				}
				next = next.rest;
			}
			p.rest = next;
			p = next;
			n++;

		}
	}
	public static IntList ilsans(IntList x, int y) {
		if(x == null) return null;
		if(x.first == y) {
			return ilsans(x.rest, y);
		}
		/* create a box only when x.first != y */
		return new IntList(x.first, ilsans(x.rest, y));

	}
	public static IntList dilsans(IntList x, int y) {
		if(x == null) return null;
		if(x.first != y) {
			x.rest = dilsans(x.rest, y);
		}

		if(x.first == y) {
			return dilsans(x.rest, y);
		}

		return x;
	}

	public static void main(String[] args) {
		IntList L = new IntList(10, null);
//		L = new IntList(2, L);
//		L = new IntList(8, L);
//		L = new IntList(2, L);
//		L = new IntList(6, L);
		L = new IntList(5, L);
		L = new IntList(2, L);
		L = new IntList(3, L);
		L = new IntList(1, L);
		L = new IntList(2, L);
//		L.skippify();
		IntList N = dilsans(L, 2);
	}
}


/* There is a bug with this solution. If the first value == y, it remains in x.
public static IntList dilsans(IntList x, int y) {
		if(x == null) return null;
		x.rest = dilsans(x.rest, y);
		if(x.first == y) {
			return x.rest;
		}

		return x;
	}
 */

