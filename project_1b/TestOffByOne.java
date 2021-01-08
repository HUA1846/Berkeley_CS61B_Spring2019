import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.

    @Test
    public void testOffByOne() {
        assertFalse(offByOne.equalChars('&', '%'));
        assertFalse(offByOne.equalChars('a', 'c'));
        assertTrue(offByOne.equalChars('a', 'b'));
        assertTrue(offByOne.equalChars('r','q'));
        assertTrue(offByOne.equalChars('y','z'));

    }

}