public class Alist2<Item> {
	private Item[] items;
	private int size;


// Java does not allow you to create generic array object, so you have to cast it with (Item[])
	public Alist2(){
		items = (Item[]) new Object[100];
		size = 0;
	}
	private void resize(int cap){
		Item[] a = (Item[]) new Object[cap];
		System.arraycopy(items, 0, a, 0, size );
		items = a;
	}
	public void addLast(Item x){
			if(size == items.length){
				resize(size * 10);
			}
	         items[size] = x;
			 size += 1;
		}

	public Item getLast(){
			return items[size-1];
		}

	public Item get(int i){
			return items[i];
		}

	public int size(){
			return size;
		}
	public Item removeLast(){
			Item last = getLast();
			items[size-1] = null;
			size -= 1;
			return last;
		}

	public static void main(String[] args){
		Alist2<Integer> L = new Alist2<>();
		L.addLast(5);
		L.addLast(10);
		L.addLast(15);
		L.addLast(20);
		System.out.println(L.removeLast());
		System.out.println(L.removeLast());
		System.out.println(L.removeLast());
	}
}