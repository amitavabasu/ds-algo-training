package training.array;
import java.util.Arrays;

/*
Given two strings, str1, and str2, where str1 contains exactly one character more than str2, find the indices of the
characters in str1 that can be removed to make str1 equal to str2.
Return the array of indices in increasing order. If it is not possible, return the array \[-1\].

**Note:** Use 0-based indexing.

**Example**

str1 = "abdgggda"

str2 = "abdggda"

Any "g" character at positions 3, 4, or 5 can be deleted to obtain str2. Return \[3, 4, 5\].
 */



    public class RemovableIndices {
        public static int[] removableIndices(String str1, String str2) {
            int n1 = str1.length(), n2 = str2.length();
            if (n1 != n2 + 1) return new int[]{-1};

            // 1) Longest common prefix length (first mismatch position)
            int L = 0;
            while (L < n2 && str1.charAt(L) == str2.charAt(L)) {
                L++;
            }

            // 2) Longest common suffix length (from the end)
            int R = 0;
            int i = n1 - 1, j = n2 - 1;
            while (j >= 0 && str1.charAt(i) == str2.charAt(j)) {
                R++;
                i--; j--;
            }

            // 3) Valid indices are from lo to hi inclusive
            int lo = n2 - R;
            int hi = L;

            if (lo > hi) return new int[]{-1};

            int[] ans = new int[hi - lo + 1];
            for (int k = 0; k < ans.length; k++) {
                ans[k] = lo + k;
            }
            return ans;
        }

        public static void main(String[] args) {
            String str1 = "aaabaaa";
            String str2 = "aabaaa";
            System.out.println(Arrays.toString(removableIndices(str1, str2))); // [3, 4, 5]
        }
    }
    /*
    L = 2
    R = 6
    lo = n2 - R = 6 - 6 = 0;
    hi = 2
     */
