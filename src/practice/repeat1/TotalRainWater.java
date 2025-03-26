package practice.repeat1;

public class TotalRainWater {

    public static int calculateTotalWater(int[] arr) {
        int totalWater = 0;
        if (arr == null || arr.length < 3) return totalWater;
        int lMaxP = 0;
        int rMaxP = arr.length - 1;
        int lMaxV = 0;
        int rMaxV = 0;
        int currentIndex = 0;
        while (lMaxP < rMaxP) {
            int currentWater = Math.min(lMaxV, rMaxV) - arr[currentIndex];
            if (currentWater > 0) {
                totalWater += currentWater;
            }
            if (lMaxV < arr[lMaxP]) {
                lMaxV = arr[lMaxP];
            }
            if (rMaxV < arr[rMaxP]) {
                rMaxV = arr[rMaxP];
            }
            if (arr[lMaxP] <= arr[rMaxP]) {
                lMaxP++;
                currentIndex = lMaxP;
            } else {
                rMaxP--;
                currentIndex = rMaxP;
            }
        }
        return totalWater;
    }



    public static void main(String[] args) {
        int[][] data = {
                {0,1,0,2,1,0,3,1,0,1,2},
                {0,0,0,10,0,0,11,0,0,0}
        };
        for (int[] d : data) {
            System.out.println(calculateTotalWater(d));
        }
    }
}
