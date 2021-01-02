
// using sentinal node
public class SLList2{
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
	/* The first item (if it exists) is at sentinal.next */
	private IntNode sentinal;

	public SLList2(){
		sentinal = new IntNode(63, null); //pick any number
		size = 0;
	}
	public SLList2(int x){
		sentinal = new IntNode(63, null);
		sentinal.next = new IntNode(x, null);
		size = 1;
	}
	public void addFirst(int x){
		sentinal.next = new IntNode(x, sentinal.next);
		size += 1;
	}
	public int getFirst(){
		return sentinal.next.item;
	}
	public void addLast(int x){
		size += 1;
		IntNode p = sentinal;
		while(p.next != null){
			p = p.next;
		}
		p.next = new IntNode(x, null);
	}
	
	public int size(){
		return size;
	}

	public static void main(String[] args){
		SLList2 L = new SLList2();
		L.addFirst(10);
		L.addFirst(5);
		L.addLast(20);
		int x = L.getFirst();
		System.out.println(L.sentinal.next.next.item); // 10
		System.out.println(L.size()); //3
	}
}