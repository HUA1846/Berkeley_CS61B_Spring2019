
// using sentinal node
public class SLL3<Type>{
	private class Node {
		public Type item;
		public Node next;

		public Node(Type i, Node n){
			item = i;
			next = n;
		}
	}
	// private IntNode first; // so users don't mess with it
	private int size;
	/* The first item (if it exists) is at sentinal.next */
	private Node sentinal;


	public SLL3(Type x){
		sentinal = new Node(x, null);
		sentinal.next = new Node(x, null);
		size = 1;
	}
	public void addFirst(Type x){
		sentinal.next = new Node(x, sentinal.next);
		size += 1;
	}
	public Type getFirst(){
		return sentinal.next.item;
	}
	public void addLast(Type x){
		size += 1;
		Node p = sentinal;
		while(p.next != null){
			p = p.next;
		}
		p.next = new Node(x, null);
	}
	
	public int size(){
		return size;
	}

	public static void main(String[] args){
		SLL3<Integer> L = new SLL3(30);
		L.addFirst(10);
		L.addFirst(5);
		L.addLast(20);
		System.out.println(L.size());
		System.out.println(L.sentinal.next.item);
		
	}
}