package training.array;

public class TrappingTotalRainWater {


    // unit of water/area[i] = min(max(left),max(right)) - current_elements_value/height

    /*
    Bruteforce O(n^2) algorithm
     */
    public static int calculateTotalTrappedWater(int[] data) {
        if (data.length < 3) return 0; //There is minimum 3 values needed to hold any water, i.e. two walls and a value between then. So min 3 is needed.
        int maxL = 0; //define and initialize variable to hold the maximum value on the left side
        int maxR = data[1]; //define and initialize variable to hold the maximum value on the left side, initialize to the next value of the left most value.
        int area = 0;//define and initialize the variable to hold total area of water
        for (int i = 0; i < data.length; i++) { //iterate over the entire array
            int currentArea = Math.min(maxL, maxR) - data[i]; //using the formula calculate water are of the current element
            if (currentArea > 0){//if the current area is grater than 0
                area = area + currentArea; // calculate total area
            }
            if (maxL < data[i]) { //compare max left with current element, if max left less than current element value, means we need to update max left as we found a grater max value
                maxL = data[i];//update max left
            }
            int j = i + 1;//initialize j to the next element of the current element
            maxR = 0; //initialize max right to 0 as we need to recalculate it by moving forward in right side
            while (j < data.length) { // until we reached end of array from current j location
                if (maxR < data[j]) { //compare max right with the element value at index j. If a grater value found update the max right
                    maxR = data[j];//update max right
                }
                j++;//increment j until end is reached
            }
        }
        return area;
    }

    /*
    Efficient algorithm with time complexity O(n)
     */
    public static int calculateTotalTrappedWater_On(int[] data) {
        int area = 0; //initialize variable to hold the total water area
        int maxL = 0;//initialize maximum value at left
        int maxR = 0;//initialize maximum value at right
        int pL = 0;//define and initialize a pointer/index at the current known maximum left value, which is 0
        int pR = data.length - 1;//define and initialize a pointer/index at the current known maximum left value, which is last index of the array
        int pC = pL;//define and initialize a pointer for the current index for which the water area getting calculated. initialize it to start of array at 0.
        //at the beginning current element pointer and current max left element pointer will point to the same location at the start of the array 0.

        while (pL < pR) {//until the max left pointer and max right pointer meets
             int currentArea = Math.min(maxL, maxR) - data[pC]; //calculate the water area with known max left and known max right
             if (currentArea > 0) { // if the calculated area grater than 0
                 area += currentArea; //update total area
             }
             if( maxL < data[pL]) { //if the current max left value less than current element value at current pointer. Then we have a new max left value.
                 maxL = data[pL];//update max left value
             }
             if (maxR < data[pR]) {//if the current max right value is less than current element value at current pointer then we have a new max right value.
                 maxR = data[pR];//update current max right value
             }
             if (data[pL] <= data[pR]) { //compare the element values at current max left pointer and max right pointer. Look for whichever is smaller (that's the main logic as the formula needs the minimum of them)
                 pL++; //if element value of max left pointer is smaller or equal, increment max left pointer
                 pC = pL;//and move the current element pointer to that same location
             } else {
                 pR--;//if the element value at the current max right pointer is less, decrement the max right pointer
                 pC = pR;//move the current element pointer to the same location of the max right value pointer
             }
        }
        return area;
    }




    public static void main(String[] args) {
        int[] data = {0,1,0,2,1,0,3,1,0,1,2};
        System.out.println(calculateTotalTrappedWater(data));
        System.out.println(calculateTotalTrappedWater_On(data));
    }
}