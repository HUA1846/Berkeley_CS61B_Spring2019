import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static CharacterComparator offby2 = new OffByN(2);
    static CharacterComparator offby5 = new OffByN(5);

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void TestIsPalindrome() {
        assertFalse(palindrome.isPalindrome("wallow"));
        assertFalse(palindrome.isPalindrome("persiflage"));
        assertTrue(palindrome.isPalindrome("racecar"));
        assertTrue(palindrome.isPalindrome("noon"));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome(""));
    }

    @Test
    public void TestIsPalidromeOBN() {
        assertTrue(palindrome.isPalindrome("froth", offby2));
        assertTrue(palindrome.isPalindrome("musk", offby2));
        assertTrue(palindrome.isPalindrome("pink", offby5));
        assertFalse(palindrome.isPalindrome("wallow", offby5));

    }
}