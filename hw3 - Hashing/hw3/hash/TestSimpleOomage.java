package hw3.hash;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;


public class TestSimpleOomage {

    @Test
    public void testHashCodeDeterministic() {
        SimpleOomage so = SimpleOomage.randomSimpleOomage();
        int hashCode = so.hashCode();
        for (int i = 0; i < 100; i += 1) {
            assertEquals(hashCode, so.hashCode());
        }
    }

    /* two SimpleOomages should EVER have the same
          hashCode UNLESS they have the same red, blue, and green values!
     */
    @Test
    public void testHashCodePerfect() {
        SimpleOomage oA = new SimpleOomage(5, 10, 20);
        SimpleOomage oA1 = new SimpleOomage(5, 10, 20);
        SimpleOomage oA2 = new SimpleOomage(5, 20, 10);
        SimpleOomage oA3 = new SimpleOomage(10, 5, 20);
        SimpleOomage oA4 = new SimpleOomage(10, 20, 5);
        SimpleOomage oA5 = new SimpleOomage(20, 10, 5);
        SimpleOomage oA6 = new SimpleOomage(20, 5, 10);
        assertNotEquals(oA, oA2);
        assertNotEquals(oA2, oA3);
        assertNotEquals(oA3, oA4);
        assertNotEquals(oA4, oA5);
        assertNotEquals(oA5, oA6);
        assertNotEquals(oA6, oA);
        assertEquals(oA, oA1);

    }

    @Test
    public void testEquals() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        SimpleOomage ooB = new SimpleOomage(50, 50, 50);
        assertEquals(ooA, ooA2);
        assertNotEquals(ooA, ooB);
        assertNotEquals(ooA2, ooB);
        assertNotEquals(ooA, "ketchup");
    }


    @Test
    public void testHashCodeAndEqualsConsistency() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        HashSet<SimpleOomage> hashSet = new HashSet<>();
        hashSet.add(ooA);
        assertTrue(hashSet.contains(ooA2));
    }

    /* TODO: Uncomment this test after you finish haveNiceHashCodeSpread in OomageTestUtility */
    @Test
    public void testRandomOomagesHashCodeSpread() {
        List<Oomage> oomages = new ArrayList<>();
        int N = 10000;

        for (int i = 0; i < N; i += 1) {
            oomages.add(SimpleOomage.randomSimpleOomage());
        }

        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(oomages, 10));
    }

    /** Calls tests for SimpleOomage. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestSimpleOomage.class);
    }
}
