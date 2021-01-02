public class AList{
	private int[] items;
	private int size;

// creates an empty List
	public AList(){
		items = new int[100];
		size = 0;
	}
	private void resize(int capacity){
		int[] a = new int[capacity];
			System.arraycopy(items, 0, a, 0, size);
			items = a;
	}
/* the next item we add will go into position size */
	public void addLast(int x){
		if(size == items.length){
			resize(size * 10);
		}
         items[size] = x;
		 size += 1;
	}

	public int getLast(){
		return items[size-1];
	}

	public int get(int i){
		return items[i];
	}

	public int size(){
		return size;
	}
	public int removeLast(){
		int last = getLast();
		size -= 1;
		return last;
	}

	public static void main(String[] args){
		AList L = new AList();
		L.addLast(5);
		L.addLast(10);
		L.addLast(15);
		L.addLast(20);
		System.out.println(L.removeLast());
		System.out.println(L.removeLast());
		System.out.println(L.removeLast());
		// int x = L.items[0];
		// System.out.println(x);
		// System.out.println(L.items[0]);
		// System.out.println(L.items[1]);
		// System.out.println(L.items[2]);
		// System.out.println(L.items[3]);
		// System.out.println(L.size());
		// System.out.println(L.getLast());
		// System.out.println(L.get(2));
	}
}