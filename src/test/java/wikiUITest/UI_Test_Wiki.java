package wikiUITest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class UI_Test_Wiki {
    protected AppiumDriver driver;

    @BeforeEach
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Pixel 2 API 28");
        capabilities.setCapability("platformVersion", "9.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "C:\\Users\\nsshletkin\\Downloads\\homeWorkTwo\\src\\main\\java\\apks\\Wikipedia_2.7.50446-r-2023-06-28_Apkpure.apk");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }


    private String buttonSkip = "org.wikipedia:id/fragment_onboarding_skip_button";
    private String placeholder = "//android.widget.TextView[@text='Search Wikipedia']";
    private String searchBox = "org.wikipedia:id/search_container";
    private String searchText ="org.wikipedia:id/search_src_text";
    private String closeButton ="org.wikipedia:id/search_close_btn";
    private String searchResults="//android.view.ViewGroup[@bounds ='[0,231][1080,428]']";
    private String footer = "//android.view.View[@bounds ='[0,1267][1080,1672]']";
    private String menuButton ="org.wikipedia:id/page_toolbar_button_show_overflow_menu";


    @Test

    public void testSwipeArtical()  {
        Helper helper = new Helper(driver);
        helper.elementClick(By.id(buttonSkip));
        helper.elementClick(By.id(searchBox));
        helper.enteringAValue(By.id(searchText),"Appium");
        helper.elementClick(By.xpath(searchResults));
        helper.swipeUpToElement(By.xpath(footer),"Эллемент не найден",20);
    }

    @Test
    public void saveFirstArticalToMyList(){
        Helper helper = new Helper(driver);
        helper.elementClick(By.id(buttonSkip));
        helper.elementClick(By.id(searchBox));
        helper.enteringAValue(By.id(searchText),"Appium");
        helper.elementClick(By.xpath(searchResults));
        helper.elementClick(By.id(menuButton));



    }


}
