import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    StudentArrayDeque<Integer> std = new StudentArrayDeque<>();
    ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();

    public int generateRandom() {
        return StdRandom.uniform(0, 10);
    }

    @Test
    public void testRemoveLast() {

        assertTrue(std.isEmpty());

        for(int i = 0; i < 10; i++) {
            int a = generateRandom();
//            int b = generateRandom();
            std.addLast(a);
            ads.addLast(a);

            assertEquals(ads.size(), std.size());
        }

        assertFalse(std.isEmpty());

        while(std.size() > 0) {
            Integer a = std.get(std.size() - 2);
            Integer b = std.get(std.size() - 1);
            Integer c = std.removeLast();
            assertEquals("addLast(" + a +")\n" + "addLast(" + b + ")\n" + "removeLast()", ads.removeLast(), c);
        }

        // test get(int i) method
        for(int i = 0; i < 10; i++) {
            int a = generateRandom();
            int actual = std.get(a);
            int expected = ads.get(a);
            assertEquals(expected, actual);
        }

    }

    @Test
    public void testRemoveFirst() {
        for(int i = 0; i < 10; i++) {
            int a = generateRandom();
            std.addFirst(a);
            ads.addFirst(a);

            assertEquals(ads.size(), std.size());
        }

        assertFalse(std.isEmpty());

        while(std.size() > 0) {
            Integer a = std.get(0);
            Integer b = std.get(1);
            Integer c = std.removeFirst();
            assertEquals("addFirst(" + b +")\n" + "addFirst(" + a + ")\n" + "removeFirst()", ads.removeFirst(), c);
        }




    }
}
