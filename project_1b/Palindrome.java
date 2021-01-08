public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> res = new LinkedListDeque<>();
        for(int i = 0; i < word.length(); i++) {
            res.addLast(word.charAt(i));
        }
        return res;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> ans = wordToDeque(word);
        if(ans.size() == 0 || ans.size() == 1) return true;

        if(ans.removeFirst() == ans.removeLast()) {
           return isPalindrome(trimWord(word));
        } else return false;
    }

    public String trimWord(String word) {
        String w = "";
        for(int i = 1; i < word.length() - 1; i++) {
            w += word.charAt(i);
        }
        return w;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> ans = wordToDeque(word);
        if(ans.size() == 0 || ans.size() == 1) return true;
        if(cc.equalChars(ans.removeFirst(), ans.removeLast())) {
            return isPalindrome(trimWord(word), cc);
        } else return false;

    }
}
