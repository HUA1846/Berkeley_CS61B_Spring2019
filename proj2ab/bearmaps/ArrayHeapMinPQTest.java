package bearmaps;

import org.junit.Test;
import static org.junit.Assert.*;

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
    public void testRemoveSmalles() {
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
    public void testChangePriority() {
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>(16);
        pq.add("Sunday", 7);
        pq.add("Saturday", 6);
        pq.add("Friday", 5);
        pq.add("Thursday", 4);
        pq.add("Wednesday", 3);
        pq.add("Tuesday", 2);
        pq.add("Monday", 1);
        pq.changePriority("Monday", 8);
        assertEquals("Tuesday", pq.getSmallest());
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
}
