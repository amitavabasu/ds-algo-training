package training.other;

import java.util.Objects;

public class BackspaceStringCompare {

    public static boolean getMatches(String S, String T) {
        Objects.requireNonNull(S, "S should not be null");
        Objects.requireNonNull(S, "T should not be null");
        if(S.equals(T)) {
            return true;
        }
        int sp = S.length()-1;
        int tp = T.length()-1;
        int sHashCount = 0;
        int tHashCount = 0;

        String sString = "";
        String tString = "";
        while (sp >= 0) {
            if (S.charAt(sp) == '#') {
                sHashCount++;
                sp--;
            } else {
                if (sHashCount > 0) {
                    sp--;
                    sHashCount--;
                } else {
                    sString = S.charAt(sp) + sString;
                    sp--;
                }
            }
        }
        while (tp >= 0) {
            if (T.charAt(tp) == '#') {
                tHashCount++;
                tp--;
            } else {
                if (tHashCount > 0) {
                    tp--;
                    tHashCount--;
                } else {
                    tString = T.charAt(tp) + tString;
                    tp--;
                }
            }
        }
        return sString.equals(tString);
    }

    public static void main(String[] args) {

        String S = "abc#d"; //<-- abd
        String T = "abzz##d"; //<-- abd


        System.out.println("Matches==> " + getMatches(S, T));
    }
}