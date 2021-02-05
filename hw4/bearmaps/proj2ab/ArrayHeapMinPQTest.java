package bearmaps.proj2ab;

import org.junit.Test;
import static org.junit.Assert.*;
import edu.princeton.cs.algs4.Stopwatch;

public class ArrayHeapMinPQTest {

    @Test
    public void testGetSmallest() {
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>(16);
        pq.add("Sunday", 7);
        pq.add("Saturday", 6);
        pq.add("Friday", 5);
        pq.add("Thursday", 4);
        pq.add("Wednesday", 3);
        pq.add("Tuesday", 2);
        pq.add("Monday", 1);
        assertEquals("Monday", pq.getSmallest());
        assertTrue(pq.contains("Sunday"));
        assertEquals(7, pq.size());
    }

    @Test
    public void testRemoveSmallest() {
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>(16);
        pq.add("Sunday", 7);
        pq.add("Saturday", 6);
        pq.add("Friday", 5);
        pq.add("Thursday", 4);
        pq.add("Wednesday", 3);
        pq.add("Tuesday", 2);
        pq.add("Monday", 1);
        assertEquals("Monday", pq.removeSmallest());
        assertEquals("Tuesday", pq.removeSmallest());
        assertEquals("Wednesday", pq.removeSmallest());
        assertEquals("Thursday", pq.removeSmallest());
        assertEquals("Friday", pq.removeSmallest());
        assertEquals("Saturday", pq.removeSmallest());
        assertEquals(1, pq.size());
        assertEquals("Sunday", pq.removeSmallest());
        assertEquals(0, pq.size());
    }
    @Test
    public void testRemoveSmallest2() {
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>(16);
        for(int i = 0; i < 10; i++) {
            pq.add("item" + i, i);
        }
        for(int i = 0; i < 9; i ++) {
            pq.removeSmallest();
        }
        assertEquals("item9", pq.removeSmallest());
    }

    @Test
    public void testChangePriority() {

        ArrayHeapMinPQ<String> pq2 = new ArrayHeapMinPQ<>(16);
        for(int i = 0; i < 100; i++) {
            pq2.add("item" + i, i + 1);
        }
        for(int i = 0; i < 100; i ++) {
            pq2.changePriority("item" + i, 99-i);
        }
        assertEquals("item99", pq2.getSmallest());
        assertTrue(pq2.contains("item99"));
        assertEquals("item99", pq2.removeSmallest());
        assertEquals("item98", pq2.getSmallest());
        assertEquals(99, pq2.size());
        assertFalse(pq2.contains("item99"));
    }
    @Test
    public void testChangePriority2() {

        ArrayHeapMinPQ<String> pq2 = new ArrayHeapMinPQ<>(16);
        for(int i = 0; i < 10; i++) {
            pq2.add("item" + i, i + 1);
        }
        for(int i = 0; i < 10; i ++) {
            pq2.changePriority("item" + i, 10-i);
        }
        assertEquals("item9", pq2.getSmallest());
        assertTrue(pq2.contains("item9"));
        assertEquals("item9", pq2.removeSmallest());
        assertEquals("item8", pq2.getSmallest());
        assertEquals(9, pq2.size());
        assertFalse(pq2.contains("item9"));
    }

    @Test
    public void testResize() {
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>(16);
        for(int i = 0; i < 200; i++) {
            pq.add("item" + i, i);
        }
        assertEquals(200, pq.size());
        assertEquals("item0", pq.getSmallest());

        for(int i = 0; i < 150; i++) {
            pq.removeSmallest();
        }
        assertEquals(50, pq.size());
        assertEquals("item50", pq.getSmallest());
    }
/*
    public void naivePQTime() {
        NaiveMinPQ<String> naive = new NaiveMinPQ<String>();
        for(int i = 0; i < 200; i++) {
            naive.add("item" + i, i);
        }
    }
    public void arrayPQTime() {
        Stopwatch sw = new Stopwatch();
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>(16);
        for(int i = 0; i < 200; i++) {
            pq.add("item" + i, i);
        }

    }

    public static void main(String[] args) {
        ArrayHeapMinPQTest pqTest = new ArrayHeapMinPQTest();
        Stopwatch sw = new Stopwatch();
        pqTest.naivePQTime();
        System.out.println("NaiveMinPQ " + sw.elapsedTime());
        pqTest.arrayPQTime();
        System.out.println("ArrayHeapMinPQ " + sw.elapsedTime());
    }

 */
}


