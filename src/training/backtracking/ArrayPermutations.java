package training.backtracking;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ArrayPermutations {
    /*
    This is a backtracking problem, because it generates all possible combinations by taking one element at a time from data array, recursively.
    Then checking if all elements are taken in each combination (i.e. data.length == 0) it is a possible permutation and prints it.
    This time it backtracks and try with previous unfinished combinations finding the next permutation.
     */
    public static int count = 0;

    public static void printPermutations(int[] permutation, int[] data) {//have two inputs, initially permutation is empty and data have all the elements
        if(data.length == 0) { //this is the condition which holds a permutation in the permutation variable because all elements are taken.
            System.out.println(Arrays.toString(permutation));//print this permutation
            count++;
        } else { //if data length is not empty, it's time to generate the permutations
            for (int i = 0; i < data.length; i++) {//for each element currently exists in data
                int[] pBar = Arrays.copyOf(permutation, permutation.length + 1);// create a new variable (pBar) to hold current permutation
                // elements in permutation variable plus one extra from the data variable, based on current index.
                pBar[permutation.length] = data[i];//add that data element at the end of newly created permutation variable called pBar.
                int[] dBar1 = Arrays.copyOfRange(data, 0, i);//create a new variable to hold first part of the data after removing the current element
                // where ever the current element is located based on the index i. that's why 0 to i
                int[] dBar2 = Arrays.copyOfRange(data, i+1, data.length);//create a new variable to hold second part of the data, after removing the
                // current element, that's why i+1 to length
                int[] dBar = IntStream.concat(Arrays.stream(dBar1), Arrays.stream(dBar2)).toArray();//create a new variable to hold the combined part of
                // first half and second half. This variable dBar will have one less element that the original data. That element is now be placed at the end of pBar.
                printPermutations(pBar, dBar);//Call the same method recursively with pBar and dBar. The call stack will build up for each element 0 to data.length
                //pBar <-- now olds one additional element from data than previous
                //dBar <-- now hold one less element based on index i
            }
        }
    }



    public static void main(String[] args) {
        int[] data = {1,2,3};
        printPermutations(new int[]{}, data);
        System.out.println("Number of permutations ==> " + count);
    }
}
