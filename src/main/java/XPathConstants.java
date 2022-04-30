public class XPathConstants {
    public static final String CHROME_DRIVER_PATH = "C:\\Program Files (x86)\\chromedriver.exe";
    public static final String INSTAGRAM_BASE_URL = "https://www.instagram.com/";

    public static final String FAKE_ACCOUNT_USERNAME = "";
    public static final String FAKE_ACCOUNT_PASSWORD = "";

    public static final String FOLLOWERS_DIV_PROFILE_PAGE = "//div[contains(text(), 'followers')]";
    public static final String NO_OF_FOLLOWERS_PROFILE_PAGE = "//div[contains(text(), 'followers')]//span";
    public static final String FOLLOWING_DIV_PROFILE_PAGE = "//div[contains(text(), 'following')]";
    public static final String NO_OF_FOLLOWING_PROFILE_PAGE = "//div[contains(text(), 'following')]//span";
    public static final String MY_PROFILE_PAGE_LINK = "//a[contains(@href,'/" + FAKE_ACCOUNT_USERNAME +"')]";
    public static final String X_LIKES_BUTTON_POST_PAGE = "//section[@class=\"EDfFK ygqzn \"]//a";
    public static final String CLOSE_COOKIES_POPUP = "//button[contains(text(), 'Only allow essential cookies')]";
    public static final String USERNAME_FIELD = "//input[@name=\"username\"]";
    public static final String PASSWORD_FIELD = "//input[@name=\"password\"]";
    public static final String LOGIN_BUTTON = "//button[@type=\"submit\"]";
    public static final String SAVE_PASS_NOT_NOW_BUTTON = "//button[contains(text(), \"Not Now\")]";
    public static final String NOTIFICATIONS_NOT_NOW_BUTTON = "//button[contains(text(), \"Not Now\")]";
    public static final String NO_OF_POST_PROFILE_PAGE = "/html/body/div[1]/section/main/div/header/section/ul/li[1]/div/span";
    public static final String POSTS_PROFILE_PAGE = "//div[@class='v1Nh3 kIKUG _bz0w']//a";
    public static final String LIKES_POPUP_SCROLL_VIEW = "/html/body/div[6]/div/div/div[2]/div/div";
    public static final String USERNAME_OF_LIKER = "//div[@class='_1XyCr  ']//span[@class='Jv7Aj mArmR MqpiF  ']//a";
    public static final String OTHERS_LINK_POST_PAGE = "//section[@class='EDfFK ygqzn ']//a//div[contains(text(), 'other')]";

    public static final String CLOSE_BUTTON_POPUP = "//div[@class='WaOAr _8E02J']//button";
    public static final String NEXT_PHOTO_BUTTON = "//div[@class=' l8mY4 feth3']//button";
    public static final String CLOSE_BUTTON_POST_VIEW = "//div[@class='NOTWr']//button";
}