package training.stack_queue_set;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FindDuplicated {

    public static boolean isDuplicate(String[][] users) {
        if (users == null || users.length == 0) return false;
        Set<String> set = new HashSet<>();
        for (String[] user : users) {
            if (user == null || user.length <= 1) continue;
            String[] ips = Arrays.copyOfRange(user, 1, users.length+1);
            Arrays.sort(ips);
            String ipString = String.join(",", ips);
            if (set.contains(ipString)){
                return true;
            } else {
                set.add(ipString);
            }
        }
        return false;
    }




    public static void main(String[] args) {
        String[][] users = {
                {"mike", "203.0.3.10", "208.51.0.5", "52.0.2.5"},
                {"bob", "111.0.0.10", "222.0.0.5", "222.0.0.8"},
                {"bob2", "222.0.0.5", "222.0.0.8", "111.0.0.10"}
        };
        System.out.println("Result ==> " + isDuplicate(users));
    }

}
