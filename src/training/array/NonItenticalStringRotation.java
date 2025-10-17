package training.array;

public class NonItenticalStringRotation {
    /*
        Given two strings s1 and s2, return 1 if s2 is a rotation of s1 but not identical to s1, otherwise return 0.

        Example

        Input:

        s1 = abcde
        s2 = cdeab
        Output:

        True
        Explanation:

        - s2 ('cdeab') is a non-trivial rotation of s1 ('abcde').
        - If you rotate 'abcde' left by 2 positions, you get 'cdeab'.
        - Since s2 is not equal to s1 and is a rotation, the output is true.
     */

    public static boolean isNonTrivialRotation(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() != str2.length()) return false;
        if (str1.equals(str2)) return false;
        String original = str1 + str1;
        int n = str2.length();
        int i = 1;
        int j = n+1;
        while (j <= original.length()) {
            if (str2.equals(original.substring(i, j))) return true;
            i++;
            j++;
        }
        return false;
    }


    public static void main(String[] args) {
        String[][] stringsArrays = {
                {"abcde", "cdeab"},
                {"ccccd", "ccdcc"},// "ccccdccccd"
        };
        for (String[] strings : stringsArrays) {
            System.out.println("String1==> " + strings[0] + "; String2==> " + strings[1] + "; Rotationally Identical==> " + isNonTrivialRotation(strings[0], strings[1]));
        }
    }

}
