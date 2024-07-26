import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static CharacterComparator cc = new OffByOne();

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
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome("racecar"));
        assertTrue(palindrome.isPalindrome("noon"));
        assertFalse(palindrome.isPalindrome("horse"));
        assertFalse(palindrome.isPalindrome("rancor"));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome(""));
    }

    @Test
    public void testIsPalindromeOffByOne() {
        assertTrue(palindrome.isPalindrome("flake", cc));
        assertTrue(palindrome.isPalindrome("a", cc));
        assertTrue(palindrome.isPalindrome("", cc));
        assertFalse(palindrome.isPalindrome("racecar", cc));
        assertFalse(palindrome.isPalindrome("noon", cc));
        assertFalse(palindrome.isPalindrome("horse", cc));
        assertFalse(palindrome.isPalindrome("rancor", cc));
    }

    @Test
    public void testIsPalindromeOffByN() {
        CharacterComparator cc5 = new OffByN(5);
        assertTrue(palindrome.isPalindrome("af", cc5));
        assertTrue(palindrome.isPalindrome("fa", cc5));
        assertTrue(palindrome.isPalindrome("a", cc5));
        assertTrue(palindrome.isPalindrome("", cc5));
        assertFalse(palindrome.isPalindrome("racecar", cc5));
        assertFalse(palindrome.isPalindrome("noon", cc5));
        assertFalse(palindrome.isPalindrome("horse", cc5));
        assertFalse(palindrome.isPalindrome("rancor", cc5));
    }
}
