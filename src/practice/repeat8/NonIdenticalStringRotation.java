package practice.repeat8;

public class NonIdenticalStringRotation {

    public static boolean isNonTrivialRotation(String s1, String s2) {
        if (s1 == null || s1.isEmpty() || s2 == null || s2.isEmpty()) return false;
        if (s1.equals(s2)) return false;
        if (s1.length() != s2.length()) return false;
        String S = s1 + s1;
        int l = 0;
        int r = s1.length();
        while (r < S.length()) {
            if (S.substring(l, r).equals(s2)) return true;
            l++;
            r++;
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
