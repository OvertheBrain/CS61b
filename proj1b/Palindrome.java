public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    public boolean isPalindrome(String s) {
        Deque<Character> words = wordToDeque(s);
        return isPalindromeHelper(words);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> words = wordToDeque(word);
        return isPalindromeHelper(words, cc);
    }

    private boolean isPalindromeHelper(Deque<Character> words) {
        if (words.size() <= 1) {
            return true;
        }
        if (words.removeFirst() != words.removeLast()) {
            return false;
        }
        return isPalindromeHelper(words);
    }

    private boolean isPalindromeHelper(Deque<Character> words, CharacterComparator cc) {
        if (words.size() <= 1) {
            return true;
        }
        if (!cc.equalChars(words.removeFirst(), words.removeLast())) {
            return false;
        }
        return isPalindromeHelper(words, cc);
    }
}
