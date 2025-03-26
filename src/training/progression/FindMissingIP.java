package training.progression;

public class FindMissingIP {

    static int n = 256;
    static int numberOfDigits = 3;
    static int base = 256;
    static double digitSum1 = ((n/2) * (2*0 + (n-1)*1)) * Math.pow(base, (numberOfDigits-1));
    static double digitSum2 = ((n/2) * (2*0 + (n-1)*1)) * Math.pow(base, (numberOfDigits-1));
    static double digitSum3 = ((n/2) * (2*0 + (n-1)*1)) * Math.pow(base, (numberOfDigits-1));
    static double digitSum4 = ((n/2) * (2*0 + (n-1)*1)) * Math.pow(base, (numberOfDigits-1));


    public static void main(String[] args) {
//        for (int digit4 = 0; digit4 < 256; digit4++) {
            for (int digit3 = 0; digit3 < 256; digit3++) {
                for (int digit2 = 0; digit2 < 256; digit2++) {
                    for (int digit1 = 0; digit1 < 256; digit1++) {
                        if (/*digit4 == 0 && */digit3 == 11 && digit2 == 11 && digit1 == 11) continue;
                        System.out.println(/*digit4 +"." +*/ digit3 + "." + digit2 + "." + digit1);
                        digitSum1 = digitSum1 - digit1;
                        digitSum2 = digitSum2 - digit2;
                        digitSum3 = digitSum3 - digit3;
//                        digitSum4 = digitSum4 - digit4;
                        System.out.println("digitSum4=" + digitSum4 +" digitSum3=" + digitSum3 + " digitSum2=" + digitSum2 + " digitSum1=" + digitSum1);
                    }
                }
            }
//        }
        System.out.println("missing IP=" /*+ digitSum4 +"."*/ + (int)digitSum3 + "." + (int)digitSum2 + "." + (int)digitSum1);
    }
}
