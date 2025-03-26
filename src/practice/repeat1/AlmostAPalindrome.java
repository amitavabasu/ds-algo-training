package practice.repeat1;

import java.util.Base64;

public class AlmostAPalindrome {
    public static String sanitizeString(String string) {
        if (string == null || string.length() == 0 ) return string;
        string = string.toLowerCase();
        StringBuilder sb = new StringBuilder();
        for (char ch : string.toCharArray()) {
            if (Character.isAlphabetic(ch) || Character.isDigit(ch)) {
                sb.append(ch);
            }
        }
        if(sb.isEmpty()) return ""; else return sb.toString();
    }

    public static boolean isAlmostPalindrome(String string) {
        if (string == null) return true;
        int l = 0;
        int r = string.length() - 1;
        while (l <= r) {
            if (string.charAt(l) != string.charAt(r)) {
                return (isPalindrome(string, l+1, r) || isPalindrome(string, l, r-1));
            }
            l++;
            r--;
        }
        return true;
    }

    public static boolean isPalindrome(String string, int l, int r) {
        while (l <= r) {
            if (string.charAt(l) != string.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

    public static void main(String[] args) {
//        String inputStrings[] = {"race a car", //<-- true
//                "abccdba", //<-- true
//                "abcdefdba", //<-- false
//                "", //<-- true
//                "a", //<-- true
//                "ab"}; //<-- true
//        for (String inputString : inputStrings) {
//            System.out.println(inputString + "==>" + sanitizeString(inputString) + "==>" + isAlmostPalindrome(sanitizeString(inputString)));
//        }
            System.out.println(new String(Base64.getDecoder().decode("MCAwLzUgKiAxLzEgKiA/ICo=")));
    }
}
