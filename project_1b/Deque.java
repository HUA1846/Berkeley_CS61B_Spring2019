public interface Deque<T> {

    public void printDeque();

    public void addFirst(T item);


    public void addLast(T item);


    public T removeFirst();

    public T removeLast();

    default public boolean isEmpty() {
      return this.size() == 0;
    }

    public int size();

    public T get(int index);

}
