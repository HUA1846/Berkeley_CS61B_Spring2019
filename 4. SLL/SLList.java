public class SLList{
	private static class IntNode {
		public int item;
		public IntNode next;

		public IntNode(int i, IntNode n){
			item = i;
			next = n;
		}
	}
	// private IntNode first; // so users don't mess with it
	private int size;
	private IntNode first;

	// public SLList(int x){
	// 	first = new IntNode(x, null);
	// 	size = 1;
	// }
	public SLList(){
		first = null;
		size = 0;
	}
	public void addFirst(int x){
		first = new IntNode(x, first);
		size += 1;
	}
	public int getFirst(){
		return first.item;
	}
	public void addLast(int x){
		size += 1;
		if(first == null) {
			first = new IntNode(x, null);
			return;
		}
		IntNode p = first;
		while(p.next != null){
			p = p.next;
		}
		p.next = new IntNode(x, null);
	}
	// I wrote this size method
	/*public int size(){
		IntNode p = first;
		if(p.next == null) return 1;
		int count = 1;
		while(p.next != null){
			p = p.next;
			count++;
		}
		return count;
	} */

	// Size method from course
	// private int size(IntNode p){
	// 	if(p.next == null) return 1;
	// 	return 1 + size(p.next);
	// }
	// public int size(){
	// 	return size(first);
	// }
	public int size(){
		return size;
	}

	public static void main(String[] args){
		SLList L = new SLList();
		L.addFirst(10);
		L.addFirst(5);
		L.addLast(20);
		int x = L.getFirst();
		// System.out.println(L.first.next.next.item); // 15
		System.out.println(L.size());
	}
}