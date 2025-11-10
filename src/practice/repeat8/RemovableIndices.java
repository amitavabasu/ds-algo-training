package practice.repeat8;

import java.util.Arrays;

public class RemovableIndices {

    public static int[] removableIndices(String s1, String s2) {
        if (s1 == null || s2 == null) return new int[]{-1};
        int n1 = s1.length();
        int n2 = s2.length();
        if (n1 != n2 + 1) return new int[]{-1};
        int L = 0;
        while (L < n2 && s1.charAt(L) == s2.charAt(L)) {
            L++;
        }
        int R = 0;
        int i = n1-1, j = n2-1;
        while(j >= 0 && s1.charAt(i) == s2.charAt(j)) {
            R++;
            i--;
            j--;
        }
        int lo = n2 - R;
        int hi = L;
        if (lo > hi) return new int[]{-1};
        int[] result = new int[hi-lo+1];
        for (int k = 0; k < result.length; k++) {
            result[k] = lo + k;
        }
        return result;
    }


    public static void main(String[] args) {
        String str1 = "aaabaaa";
        String str2 = "aabaaa";
        System.out.println(Arrays.toString(removableIndices(str1, str2))); // [3, 4, 5]
    }
}
