package training.search_sort;

public class AlmostAPalindrome {

    public static String sanitizeString(String inputString) {
        if (inputString == null || "".equals(inputString)) {
            return inputString;
        }
        StringBuilder sb = new StringBuilder();
        for (Character ch : inputString.toLowerCase().toCharArray()) {
            if (Character.isAlphabetic(ch) || Character.isDigit(ch)) {
                sb.append(ch);
            }
        }
        if(sb.isEmpty()) return ""; else return sb.toString();

    }

    public static boolean isPalindrome(String inputString, int li, int ri) {
        while (li <= ri) {
            if(inputString.charAt(li) != inputString.charAt(ri)) {
                return false;
            }
            li++;
            ri--;
        }
        return true;
    }

    public static boolean isAlmostPalindrome(String inputString) {
        if(inputString == null) return true;
        int li = 0;
        int ri = inputString.length() - 1;

        while (li <= ri) {
            if(inputString.charAt(li) != inputString.charAt(ri)) {
                return isPalindrome(inputString, li+1, ri) || isPalindrome(inputString, li, ri-1);
            }
            li++;
            ri--;
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