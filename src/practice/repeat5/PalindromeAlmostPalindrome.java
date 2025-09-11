package practice.repeat5;

public class PalindromeAlmostPalindrome {

    public static String sanitizeString(String input) {
        if (input == null || input.length() == 0) return "";
        StringBuilder sb = new StringBuilder();
        for (char c : input.toLowerCase().toCharArray()) {
            if (Character.isAlphabetic(c) || Character.isDigit(c)) {
                sb.append(c);
            }
        }
        if (sb.isEmpty()) return ""; else return sb.toString();
   }

    public static boolean isPalindrome(String input, int l, int r) {
        if (input == null || input.isEmpty()) return true;
        while (l < r) {
            if (input.charAt(l) != input.charAt(r)) {
                return false;
            } else {
                l++;
                r--;
            }
        }
        return true;
    }

    public static boolean isAlmostPalindrome(String input) {
        int l = 0;
        int r = input.length()-1;
        while (l < r) {
            if (input.charAt(l) != input.charAt(r)) {
                return (isPalindrome(input, l, r - 1)) || isPalindrome(input, l + 1, r);
            }
            l++;
            r--;
        }
        return true;
    }



    public static void main(String[] args) {
        String inputStrings[] = {"race a car", //<-- true
                "abccdba", //<-- true
                "abcdefdba", //<-- false
                "", //<-- true
                "a", //<-- true
                "ab"}; //<-- true
        for (String inputString : inputStrings) {
            System.out.println(inputString + "==>" + sanitizeString(inputString) + "==>" + isAlmostPalindrome(sanitizeString(inputString)));
        }
    }
}
