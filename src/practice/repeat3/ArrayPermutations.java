package practice.repeat3;

public class ArrayPermutations {

    public static void printAllPermutations(String s, String p) {
        if ("".equals(s)) {
            System.out.println(p);
        }
        for (char c : s.toCharArray()) {
            String pBar = p+c;
            String sBarFirstPart = s.substring(0, s.indexOf(c));
            String sBarSecondPart = s.substring(s.indexOf(c)+1, s.length());
            String sBar = sBarFirstPart+sBarSecondPart;
            printAllPermutations(sBar, pBar);
        }
    }




    public static void main(String[] args) {
        String text = "123";
        printAllPermutations(text, "");
    }
}
