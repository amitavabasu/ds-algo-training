package practice.repeat8;

public class TappingRainWater {


    public static int calculateTotalTrappedWater(int[] arr) {
        if (arr == null || arr.length <= 1) return 0;
        int area = 0;
        int maxL = 0;
        int maxR = 0;
        int pL = 0;
        int pR = arr.length - 1;
        int pC = pL;
        while (pL < pR) {
            int currentArea = Math.min(maxL, maxR) - arr[pC];
            if (currentArea > 0) area += currentArea;
            if (maxL < arr[pL]) maxL = arr[pL];
            if (maxR < arr[pR]) maxR = arr[pR];
            if ( arr[pL] < arr[pR]) {
                pL++;
                pC = pL;
            } else {
                pR--;
                pC = pR;
            }
        }
        return area;
    }


    public static void main(String[] args) {
        int[] data = {0,1,0,2,1,0,3,1,0,1,2};
        System.out.println(calculateTotalTrappedWater(data));
    }
}
