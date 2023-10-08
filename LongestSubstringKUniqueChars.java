import java.util.HashMap;
import java.util.Map;

public class LongestSubstringKUniqueChars {

    public static int longestSubstringKUniqueChars(String s, int k) {
        if (s == null || s.length() == 0 || k <= 0) {
            return -1; // Invalid input
        }

        int start = 0;
        int maxLen = -1; // Initialize maxLen to -1 (no valid substring found)
        Map<Character, Integer> charCount = new HashMap<>();

        for (int end = 0; end < s.length(); end++) {
            char currentChar = s.charAt(end);
            charCount.put(currentChar, charCount.getOrDefault(currentChar, 0) + 1);

            // Shrink the window if there are more than K unique characters
            while (charCount.size() > k) {
                char leftChar = s.charAt(start);
                charCount.put(leftChar, charCount.get(leftChar) - 1);
                if (charCount.get(leftChar) == 0) {
                    charCount.remove(leftChar);
                }
                start++;
            }

            // Update maxLen if a valid substring is found
            if (charCount.size() == k) {
                maxLen = Math.max(maxLen, end - start + 1);
            }
        }

        return maxLen;
    }

    public static void main(String[] args) {
        String s = "aabacbebebe";
        int k = 3;
        int result = longestSubstringKUniqueChars(s, k);
        System.out.println("Longest Substring with " + k + " Unique Characters: " + result);
    }
}
