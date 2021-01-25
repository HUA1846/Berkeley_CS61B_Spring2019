package bearmaps;

import java.util.*;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private int capacity;
    private Entry[] minHeap;
    private int size;
    private int pos;
    private Map<T, Integer> keys = new HashMap<>();

    public ArrayHeapMinPQ(int capacity) {
        this.capacity = capacity;
        minHeap = new Entry[capacity + 1]; // leave 0 empty
        size = 0;
        pos = 1;
    }

    public class Entry<T> {
        T item;
        double priority;

        public Entry(T i, double p) {
            item = i;
            priority = p;
        }
        public void setPriority(double p) {
            this.priority = p;
        }
    }
/* Adds an item of type T with the given priority. If the item already exists,
   throw an IllegalArgumentException. You may assume that item is never null. */
    @Override
    public void add(T item, double priority) {
        if(contains(item)) {
            throw new IllegalArgumentException("this item already exists");
        }
        minHeap[pos] = new Entry(item, priority);
        keys.put(item, pos);
        bubbleUp(pos);
        pos += 1;
        size += 1;
        if(size == minHeap.length - 1) {
            resize();
        }

    }

    /* Returns true if the PQ contains the given item. */
    @Override
    public boolean contains(T item) {
        return keys.containsKey(item);
    }
    /* Returns the item with smallest priority. If no items exist, throw a NoSuchElementException. */
    @Override
    public T getSmallest() {
        if(size == 0) {
            throw new NoSuchElementException();
        }
        return (T) minHeap[1].item;
    };
    /* Removes and returns the item with smallest priority. If no items exist, throw a NoSuchElementException */
    @Override
    public T removeSmallest() {
        if(size == 0) {
            throw new NoSuchElementException();
        }
        T returnItem = (T)minHeap[1].item;
        keys.remove(returnItem);
        minHeap[1] = minHeap[pos - 1];
        minHeap[pos - 1] = null;
        pos -= 1;
        size -= 1;
        sinkDown(1);
        if((double) size/minHeap.length < 0.25) {
            resize();
        }
        return returnItem;
    }
    @Override
    public int size() {
        return size;
    }


    /* Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    @Override
    public void changePriority(T item, double priority) {
        if(!contains(item)) {
            throw new NoSuchElementException();
        }
        int index = find(item);
        minHeap[index].setPriority(priority);
        if(minHeap[index].priority < minHeap[parentIdx(index)].priority) {
            bubbleUp(index);
        } else {
            sinkDown(index);
        }
    }
    private int find(T item) {
        return keys.get(item);
    }

    private void resize() {
        if(size == minHeap.length - 1) {
            capacity *= 2;
        }
        if((double) size/minHeap.length < 0.25) {
            capacity /= 2;
        }
        Entry[] copy = new Entry[capacity + 1];
        System.arraycopy(minHeap, 1, copy, 1, size);
        minHeap = copy;
    }
    private int smallerChild(int n) {
       if(minHeap[n*2].priority < minHeap[n*2 + 1].priority) {
           return n*2;
       } else {
           return n*2 + 1;
       }
    }

    private void sinkDown(int n) {
        if(n >= pos/2) return;
        if(minHeap[n].priority < minHeap[n * 2].priority &&
                minHeap[n].priority < minHeap[n * 2 + 1].priority) {
            return;
        }
        else {
            swap(minHeap[n], minHeap[smallerChild(n)]);
            sinkDown(smallerChild(n));
        }
    }
    private void bubbleUp(int n) {
        if(n > 1 && minHeap[n].priority < minHeap[parentIdx(n)].priority) {
            swap(minHeap[n], minHeap[parentIdx(n)]);
            bubbleUp(parentIdx(n));
        }
    }
    private void swap(Entry e1, Entry e2) {
        T temp = (T)e1.item;
        double p = e1.priority;
        int index1 = find((T)e1.item);
        int index2 = find((T)e2.item);
        keys.replace((T) e1.item, index2);
        keys.replace((T) e2.item, index1);
        e1.item = e2.item;
        e1.priority = e2.priority;
        e2.item = temp;
        e2.priority = p;

    }

    private int parentIdx(int n) {
        if(n == 1) return 1;
        return n/2;
    }
    /* Returns the number of items in the PQ. */

}
