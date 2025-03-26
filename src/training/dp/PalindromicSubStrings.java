package training.dp;

public class PalindromicSubStrings {

    public static boolean isPalindrome(String s, int l, int r) {
        while (l <= r) {
            if (s.charAt(l) != s.charAt(r)) return false;
            l++;
            r--;
        }
        return true;
    }

    public static int findPalindromicSubstringCountBruteForce(String s) {
        if (s == null || s.length() == 0) return 0;
        if (s.length() == 1) return 1;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (isPalindrome(s,i,j)) count++;
            }
        }
        return count;
    }

    public static int findPalindromicSubstringCount(String s) {
        if (s == null || s.length() == 0) return 0;
        if (s.length() == 1) return 1;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            int evenCount = countPalindromes(s, i, i + 1);
            int oddCount = countPalindromes(s, i - 1, i + 1);
            count = count + evenCount + oddCount + 1;
        }
        return count;
    }

    public static int countPalindromes(String s, int l, int r) {
        int count = 0;
        while (l >= 0 && l < s.length() && r >= 0 && r < s.length()) {
            if (s.charAt(l) == s.charAt(r)) {
                count++;
                l--;
                r++;
            } else {
                break;
            }
        }
        return count;
    }

    public static int findPalindromicSubstringCountDP(String s) {
        if (s == null || s.length() == 0) return 0;
        if (s.length() == 1) return 1;
        int count = 0;
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        //for length == 1
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
            count++;
        }
        //for length = 2;
        for (int i = 0; i < n-1; i++) {
            if (s.charAt(i) == s.charAt(i+1)) {
                dp[i][i+1] = true;
                count++;
            }
        }
        //for length 3 and more
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i < n-len+1; i++) {
                int endIndex = i+len-1;
                if (s.charAt(i) == s.charAt(endIndex) && dp[i + 1][endIndex - 1]) {
                    dp[i][endIndex] = true;
                    count++;
                }
            }
        }
        return count;
    }

    public static int findPalindromicSubstringCountDPSimplified(String s) {
        if (s == null || s.length() == 0) return 0;
        if (s.length() == 1) return 1;
        int count = 0;
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i < n-len+1; i++) {
                int endIndex = i+len-1;
                if (s.charAt(i) == s.charAt(endIndex) && (len <= 2 || dp[i+1][endIndex-1])) {
                    dp[i][endIndex] = true;
                    count++;
                }
            }
        }
        return count;
    }


    public static void main(String[] args) {
        String[] arrays = {
                "abc",
                "aaa",
                "fdsklf"
        };
        for(int i = 0; i<arrays.length;i++) {
            System.out.println(i + " ==> " + findPalindromicSubstringCountBruteForce(arrays[i]));
            System.out.println(i + " ==> " + findPalindromicSubstringCount(arrays[i]));
            System.out.println(i + " ==> " + findPalindromicSubstringCountDP(arrays[i]));
            System.out.println(i + " ==> " + findPalindromicSubstringCountDPSimplified(arrays[i]));
        }
    }

}
