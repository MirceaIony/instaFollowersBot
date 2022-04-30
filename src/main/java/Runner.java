import java.io.*;
import java.util.*;

public class Runner {

    public static void main(String[] args) throws InterruptedException, IOException {
        System.setProperty("webdriver.chrome.driver", XPathConstants.CHROME_DRIVER_PATH);

        InstaBot instaBot = new InstaBot();

        instaBot.openMyProfilePage();
        Util.printToFile(instaBot.likes, "likes.txt");
        instaBot.getFollowers();
        Util.printToFile(instaBot.followers, "followers.txt");
        instaBot.getFollowing();
        Util.printToFile(instaBot.following, "following.txt");

        Set<String> followersThatDontLike = Util.listDiff(instaBot.followers, instaBot.likes);
        Util.printToFile(followersThatDontLike, "followersThatDontLike.txt");

        Set<String> followingThatDontLike = Util.listDiff(instaBot.following, instaBot.likes);
        Util.printToFile(followingThatDontLike, "followingThatDontLike.txt");

//        computeData();

//        Util.refactorUsersFile();
    }

    private static void computeData() throws IOException {
        Set<String> followers = Util.fileToSet("followers.txt");
        Set<String> following = Util.fileToSet("following.txt");
        Set<String> likes = Util.fileToSet("likes.txt");

        Set<String> followersThatDontLike = Util.listDiff(followers, likes);
        Set<String> followingThatDontLike = Util.listDiff(following, likes);
        Util.printToFile(followersThatDontLike, "followersThatDontLike.txt");
        Util.printToFile(followingThatDontLike, "followingThatDontLike.txt");
    }
}