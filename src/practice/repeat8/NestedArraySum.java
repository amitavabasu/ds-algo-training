package practice.repeat8;

public class NestedArraySum {

    public static int getSum(Object[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int sum = 0;
        for (Object o : arr) {
            if (o.getClass().isAssignableFrom(Integer.class)) {
                sum += (Integer)o;
            } else {
                sum += getSum((Object[])o);
            }
        }
        return sum;
    }


    public static void main(String[] args) {
        Object[][] data ={
                {1, new Object[]{2,3}, new Object[]{4, new Object[]{5}}, 6},
                {new Object[]{new Object[]{new Object[]{1}}, 2}},
                {},
                {-1, new Object[]{-2, 3}, new Object[]{4, new Object[]{-5}}, 6}
        };
            for (Object[] o : data) {
            System.out.println("Result ==> " + getSum(o));
        }
    }

}
