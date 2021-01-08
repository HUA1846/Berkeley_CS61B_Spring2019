import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {

    OffByN offBy2 = new OffByN(2);
    OffByN offBy3 = new OffByN(3);
    OffByN offBy5 = new OffByN(5);

    @Test
    public void TestOffBy2() {

        assertTrue(offBy2.equalChars('a', 'c'));
        assertTrue(offBy2.equalChars('d', 'b'));
        assertFalse(offBy2.equalChars('a', 'z'));

    }
    @Test
    public void TestOffBy3() {

        assertTrue(offBy3.equalChars('a', 'd'));
        assertTrue(offBy3.equalChars('e', 'b'));
        assertFalse(offBy3.equalChars('m', 'o'));

    }

    @Test
    public void TestOffBy5() {

        assertTrue(offBy5.equalChars('a', 'f'));
        assertTrue(offBy5.equalChars('g', 'b'));
        assertFalse(offBy5.equalChars('y', 'z'));

    }
}
