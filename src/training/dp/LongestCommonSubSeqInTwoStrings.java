package training.dp;

public class LongestCommonSubSeqInTwoStrings {

    public static int longestSubSequence(String text1, String text2) {
        if (text1 == null || text1.length() == 0 || text2 == null || text2.length() == 0) return 0;
        if (text1.equals(text2)) return text1.length();
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        for (int i = 0; i < text1.length(); i++) {
            for (int j = 0; j < text2.length(); j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }

    public static void main(String[] args) {
        String[][] arrays = {
                {"abcde", "ace"},
                {"abc", "abc"},
                {"abc", "def"},
                {"aabcde", "acea"}
        };
        for(int i = 0; i<arrays.length;i++) {
            System.out.println(i + " ==> " + longestSubSequence(arrays[i][0], arrays[i][1]));
        }
    }
}
