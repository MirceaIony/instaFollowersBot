import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class InstaBot {
    WebDriver driver;
    Set<String> likes = new TreeSet<>();
    Set<String> followers = new TreeSet<>();
    Set<String> following = new TreeSet<>();
    int scanned = 0;

    InstaBot() throws InterruptedException {
        setup();
        login();
//        driver.get(XPathConstants.INSTAGRAM_BASE_URL + username + "/");
    }

    public void getFollowers() throws InterruptedException {
        driver.findElement(By.xpath(XPathConstants.FOLLOWERS_DIV_PROFILE_PAGE)).click();
        Integer noOfFollowers = Integer.valueOf(driver.findElement(By.xpath(XPathConstants.NO_OF_FOLLOWERS_PROFILE_PAGE)).getAttribute("title"));

        while (followers.size() != noOfFollowers) {
            // step 1
            List<WebElement> elements = driver.findElements(By.xpath(XPathConstants.USERNAME_OF_LIKER));

            // step 2
            elements.forEach(element -> {
                followers.add(element.getAttribute("title"));
            });

            // step 3
            if (elements.size() > 0) {
                ((JavascriptExecutor)driver).executeScript("return arguments[0].scrollIntoView();", elements.get(elements.size()-1));
                Thread.sleep(Util.random(1000, 1200));
            }
        }
        driver.findElement(By.xpath(XPathConstants.CLOSE_BUTTON_POPUP)).click();
        Thread.sleep(Util.random(1000, 1200));
    }

    public void getFollowing() throws InterruptedException {
        driver.findElement(By.xpath(XPathConstants.FOLLOWING_DIV_PROFILE_PAGE)).click();
        Integer noOfFollowing = Integer.valueOf(driver.findElement(By.xpath(XPathConstants.NO_OF_FOLLOWING_PROFILE_PAGE)).getText());

        while (following.size() != noOfFollowing) {
            // step 1
            List<WebElement> elements = driver.findElements(By.xpath(XPathConstants.USERNAME_OF_LIKER));
            Thread.sleep(Util.random(1000, 1200));

            // step 2
            elements.forEach(element -> {
                following.add(element.getAttribute("title"));
            });

            // step 3
            if (elements.size() > 0) {
                ((JavascriptExecutor)driver).executeScript("return arguments[0].scrollIntoView();", elements.get(elements.size()-1));
                Thread.sleep(Util.random(1000, 1200));
            }
        }
        driver.findElement(By.xpath(XPathConstants.CLOSE_BUTTON_POPUP)).click();
        Thread.sleep(Util.random(1000, 1200));
    }

    private Set<String> getListOfLikes() throws InterruptedException {
        Set<String> users = new HashSet<>();

        String height;
        boolean likesAreHidden = false;

        List<WebElement> heightHiddenLikes = driver.findElements(By.xpath("/html/body/div[7]/div/div/div[3]/div/div"));
        Thread.sleep(Util.random(1000, 1200));
        if (!heightHiddenLikes.isEmpty()) {
            likesAreHidden = true;
            height = heightHiddenLikes.get(0).getCssValue("padding-top");
        } else {
            height = driver.findElement(By.xpath("/html/body/div[7]/div/div/div[2]/div/div")).getCssValue("padding-top");
        }
        Thread.sleep(Util.random(1000, 1200));
        boolean match = false;
        while (!match) {
            String lastHeight = height;

            // step 1
            List<WebElement> elements = driver.findElements(By.xpath(XPathConstants.USERNAME_OF_LIKER));
            Thread.sleep(Util.random(2000, 2200));

            // step 2
            elements.forEach(element -> {
                users.add(element.getAttribute("title"));
            });

            // step 3
            ((JavascriptExecutor)driver).executeScript("return arguments[0].scrollIntoView();", elements.get(elements.size()-1));
            Thread.sleep(Util.random(1000, 1200));

            if (likesAreHidden) {
                height = driver.findElement(By.xpath("/html/body/div[7]/div/div/div[3]/div/div")).getCssValue("padding-top");
            } else {
                height = driver.findElement(By.xpath("/html/body/div[7]/div/div/div[2]/div/div")).getCssValue("padding-top");
            }
            if(lastHeight.equals(height)) {
                match = true;
            }
        }
        scanned += 1;
        return users;
    }

    private Set<String> getAllPhotosUrls() throws InterruptedException {
        Set<String> photoUrls = new HashSet<>();

        String numberOfPosts = driver.findElement(By.xpath(XPathConstants.NO_OF_POST_PROFILE_PAGE)).getText();
        boolean match = false;
        while (!match) {

            // step 1
            List<WebElement> elements = driver.findElements(By.xpath(XPathConstants.POSTS_PROFILE_PAGE));

            // step 2
            elements.forEach(element -> {
                photoUrls.add(element.getAttribute("href"));
            });

            // step 3
            ((JavascriptExecutor)driver).executeScript("return arguments[0].scrollIntoView();", elements.get(elements.size()-1));
            Thread.sleep(1000);

            if (photoUrls.size() == Integer.valueOf(numberOfPosts)) {
                match = true;
            }
        }
        return photoUrls;
    }

    private void openLikesPopup() throws InterruptedException {
        driver.findElement(By.xpath(XPathConstants.X_LIKES_BUTTON_POST_PAGE)).click();
        Thread.sleep(1000);
    }

    private void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
    }

    private void login() throws InterruptedException {
        driver.get(XPathConstants.INSTAGRAM_BASE_URL);
        Thread.sleep(1000);
        driver.findElement(By.xpath(XPathConstants.CLOSE_COOKIES_POPUP)).click();

        Thread.sleep(2000);
        driver.findElement(By.xpath(XPathConstants.USERNAME_FIELD)).sendKeys(XPathConstants.FAKE_ACCOUNT_USERNAME);
        driver.findElement(By.xpath(XPathConstants.PASSWORD_FIELD)).sendKeys(XPathConstants.FAKE_ACCOUNT_PASSWORD);
        Thread.sleep(1500);
        driver.findElement(By.xpath(XPathConstants.LOGIN_BUTTON)).click();
        Thread.sleep(3000);

        // Close save pass popup
        driver.findElement(By.xpath(XPathConstants.SAVE_PASS_NOT_NOW_BUTTON)).click();
        Thread.sleep(2000);

        // Close notifications popup
        driver.findElement(By.xpath(XPathConstants.NOTIFICATIONS_NOT_NOW_BUTTON)).click();
        Thread.sleep(2000);
    }

    public void getAllUsersWhoEverLikedAnyPost() throws InterruptedException {
        Set<String> photoUrls = getAllPhotosUrls();
        System.out.println("No of posts: " + photoUrls.size());
        photoUrls.forEach(url -> {
            driver.get(url);
            try {
                Thread.sleep(1000);
                if (!driver.findElements(By.xpath(XPathConstants.X_LIKES_BUTTON_POST_PAGE)).isEmpty()){
                    openLikesPopup();
                    likes.addAll(getListOfLikes());
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                System.out.println("No of likes: " + likes.size());
                e.printStackTrace();
            }
        });
        System.out.println("FINAL RESULTS: Scanned " + scanned + "photos");

        System.out.println("FINAL RESULTS: No of likes: " + likes.size());
        likes.forEach(like -> System.out.println(like));
    }

    public void openMyProfilePage() throws InterruptedException {
        //Open my profile
        driver.findElement(By.xpath(XPathConstants.MY_PROFILE_PAGE_LINK)).click();
        Thread.sleep(Util.random(2000, 2200));

        driver.findElement(By.xpath(XPathConstants.POSTS_PROFILE_PAGE)).click();
        Thread.sleep(Util.random(2000, 2200));

        boolean nextExists = true;
        while (nextExists) {
            getLikesForPhoto();

            List<WebElement> nextPostButton = driver.findElements(By.xpath(XPathConstants.NEXT_PHOTO_BUTTON));
            Thread.sleep(Util.random(500, 600));
            nextExists = !nextPostButton.isEmpty();

            if (nextExists) {
                nextPostButton.get(0).click();
            }
            Thread.sleep(Util.random(1000, 1200));
        }
        driver.findElement(By.xpath(XPathConstants.CLOSE_BUTTON_POST_VIEW)).click();
        Thread.sleep(Util.random(1000, 1200));
    }

    private void getLikesForPhoto() throws InterruptedException {
        List<WebElement> othersLink = driver.findElements(By.xpath(XPathConstants.OTHERS_LINK_POST_PAGE));
        Thread.sleep(Util.random(500, 600));
        if (!othersLink.isEmpty()) {
            othersLink.get(0).click();
            likes.addAll(getListOfLikes());
            Thread.sleep(Util.random(1000, 1200));
            driver.findElement(By.xpath(XPathConstants.CLOSE_BUTTON_POPUP)).click();
        }
    }
}