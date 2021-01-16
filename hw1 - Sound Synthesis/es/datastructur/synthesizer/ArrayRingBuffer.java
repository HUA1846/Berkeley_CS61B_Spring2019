package es.datastructur.synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;

    }

    @Override
    public void enqueue(T x) {
        if(fillCount == rb.length) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        last = plusOne(last);
        fillCount += 1;
    }
    private int plusOne(int x) {
        if(x == rb.length - 1) {
            x = 0;
        } else {
            x += 1;
        }
        return x;
    }
    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if(fillCount == 0) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T removed = rb[first];
        rb[first] = null;
        first = plusOne(first);
        fillCount -= 1;
        return removed;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        return rb[first];
    }

    @Override
    public int capacity(){
        return rb.length;
    }

    @Override
    public int fillCount(){
        return fillCount;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayBufferIterator();
    }

    private class ArrayBufferIterator implements Iterator<T> {
        private int pos;
        private int count;

        public ArrayBufferIterator() {
            pos = first;
            count = 0;
        }

        public boolean hasNext() {
            return count < rb.length;
        }

        public T next() {
            T item = rb[pos];
            pos = plusOne(pos);
            count += 1;

            return item;
        }
    }

    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.
}
    // TODO: Remove all comments that say TODO when you're done.
