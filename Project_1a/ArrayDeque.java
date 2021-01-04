public class ArrayDeque<T> {
	private T[] items;
	private int size;
	private int nextFirst;
	private int nextLast;
	private int capacity;
	private int initialLength = 8;

	public ArrayDeque(){
		capacity = initialLength;
		items = (T[]) new Object[capacity];
		size = 0;
		nextFirst = capacity - 1;
		nextLast = 0;
	}
	public ArrayDeque(ArrayDeque other) {
		capacity = other.capacity;
		items = (T[]) new Object[capacity];
		size = other.size;
		nextFirst = other.nextFirst;
		nextLast = other.nextLast;
		System.arraycopy(other.items, 0, items, 0, capacity);
	}

	public int resize(int cap){
		if(size == cap) {
			int newCapacity = cap * 2;
			capacity = newCapacity;
			expand();
		}
		if((double) size / (double)capacity < 0.25 && capacity > initialLength){
			int newCapacity = cap / 2;
			capacity = newCapacity;
			shrink();
		}
		return capacity;
	}
	public void expand(){
		T[] newArray = (T[]) new Object[capacity];
		int currentFirstIdx = plusOne(nextFirst);
		int firstLength = items.length - (currentFirstIdx);
		int lastLength = nextLast;
		int newFirst = capacity - firstLength;
		System.arraycopy(items, currentFirstIdx, newArray, newFirst, firstLength);
		System.arraycopy(items, 0, newArray, 0, lastLength);

		nextFirst = minusOne(newFirst);
		items = newArray;
	}
	public void shrink(){
		T[] newArray = (T[]) new Object[capacity];
		if(nextFirst < nextLast) {
			int headIdx = nextFirst + 1;
			System.arraycopy(items, headIdx, newArray, 0, size );
			nextFirst = capacity - 1;
			nextLast = size;
		} else {
			int headIdx = nextFirst + 1;
			int firstLength = items.length - nextFirst - 1;
			int newHeadIdx = capacity - firstLength;
			int lastLength = size - firstLength;
			System.arraycopy(items, headIdx, newArray, newHeadIdx, firstLength);
			System.arraycopy(items, 0, newArray, 0, lastLength);
			nextFirst = newHeadIdx - 1;
		}

		items = newArray;

	}

	private int minusOne(int index){
		if(index == 0) {
			return items.length - 1;
		} else {
			return index -1;
		}
	}
	private int plusOne(int index){
		if(index == items.length - 1){
			return 0;
		} else {
			return index + 1;
		}
	}
	public void printDeque() {
		int currentFirst = plusOne(nextFirst);
		while (currentFirst != nextLast) {
			System.out.print(items[currentFirst] + " ");
			currentFirst = plusOne(currentFirst);
		}

	}
	public void addFirst(T item){
		items[nextFirst] = item;
		size += 1;
		nextFirst = minusOne(nextFirst);

		if(size == capacity) {
			resize(capacity);
		}
	}

	public void addLast(T item){
		items[nextLast] = item;
		size += 1;
		nextLast = plusOne(nextLast);
		if(size == capacity) {
			resize(capacity);
		}

	}
	public T removeFirst(){
		int removeIdx;
		if(nextFirst == (capacity - 1)) {
			removeIdx = nextLast - size;
		} else {
			removeIdx = nextFirst + 1;
		}
		T item = items[removeIdx];
		items[removeIdx] = null;
		nextFirst = removeIdx;
		size -= 1;
		if((double) size / (double)capacity < 0.25 && capacity > initialLength){
			resize(capacity);
		}

		return item;
	}
	public T removeLast(){
		int removeIdx;
		if(nextLast == 0) {
			removeIdx = capacity - 1;
		} else {
			removeIdx = nextLast - 1;
		}
		T item = items[removeIdx];
		items[removeIdx] = null;
		nextLast = removeIdx;
		size -= 1;
		if((double) size / (double)capacity < 0.25 && capacity > initialLength){
			resize(capacity);
		}

		return item;
	}
	public boolean isEmpty(){
		return size == 0;
	}
	public int size(){
		return size;
	}
	public T get(int index){
		return items[index];
	}

	public static void main(String[] args){
		ArrayDeque<Integer> L = new ArrayDeque<>();

		L.addFirst(5);
		L.addFirst(4);
		L.addFirst(3);
		L.addFirst(2);
		L.addFirst(1);
		L.addLast(30);
		L.addLast(40);
		L.addLast(50);
		L.addLast(60);
		L.addLast(70);
		L.addLast(80);
//		L.removeFirst();
//		L.removeFirst();
//		L.removeFirst();
//		L.removeLast();
//		L.removeLast();
//		L.removeLast();
//		L.removeLast();
//		L.removeLast();
//		ArrayDeque<Integer> N = new ArrayDeque<>(L);
		L.printDeque();
	}
}

