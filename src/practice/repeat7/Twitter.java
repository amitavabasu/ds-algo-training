package practice.repeat7;

import java.util.*;

public class Twitter {

    private final Map<Integer, List<Integer>> followerMap = new HashMap<>();
    private final Map<Integer, List<String>> feedMap = new HashMap<>();

    public void subscribe(int id) {
        if (!followerMap.containsKey(id)) {
            List<Integer> followers = new LinkedList<>();
            followerMap.put(id, followers);
        }
        if (!feedMap.containsKey(id)) {
            List<String> feeds = new LinkedList<>();
            feedMap.put(id, feeds);
        }
    }

    public void follow (int followerId, int id) {
        List<Integer> followers = followerMap.get(id);
        if (followers == null) throw new RuntimeException("Not subscribed: " + id);
        followers.add(followerId);
    }

    public void tweet(int id, String tweet) {
        List<Integer> followers = followerMap.get(id);
        if (followers == null) throw new RuntimeException("No followers for: " + id);
        for (int followerId : followers) {
            List<String> feeds = feedMap.get(followerId);
            if (feeds == null) feeds = new LinkedList<>();
            feeds.add(tweet);
        }
    }

    public List<String> getFeed(int id) {
        List<String> feeds = feedMap.get(id);
        if (feeds == null) return Collections.emptyList();
        return feeds;
    }


    public static void main(String[] args) {
        Twitter tw = new Twitter();
        tw.subscribe(1);
        tw.subscribe(2);
        tw.subscribe(3);
        tw.subscribe(4);
        tw.follow(1,2);
        tw.follow(1,3);
        tw.follow(1,4);
        tw.follow(3,2);
        tw.follow(3,1);
        tw.follow(4,3);
        tw.tweet(1, "tweet by 1");
        tw.tweet(2, "tweet by 2");
        tw.tweet(3, "tweet by 3");
        tw.tweet(4, "tweet by 4");
        tw.tweet(2, "second tweet by 2");
        System.out.println(Arrays.toString(tw.getFeed(1).toArray()));
        System.out.println(Arrays.toString(tw.getFeed(2).toArray()));
        System.out.println(Arrays.toString(tw.getFeed(3).toArray()));
        System.out.println(Arrays.toString(tw.getFeed(4).toArray()));
    }
}
