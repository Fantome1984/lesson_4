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
        capabilities.setCapability("orientation","PORTRAIT");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }


    private String buttonSkip = "org.wikipedia:id/fragment_onboarding_skip_button";
    private String searchBox = "org.wikipedia:id/search_container";
    private String searchText ="org.wikipedia:id/search_src_text";
    private String serchResultOne ="//android.widget.TextView[@text='High-level programming language']";
    private String serchResultTwo ="//android.widget.TextView[@text='Java (programming language)']";
    private String buttonSave = "org.wikipedia:id/page_save";
    private String buttonAnotherReadingList = "//android.widget.TextView[@text='Add to another reading list']";
    private String createNewReadingList = "//android.widget.TextView[@text='Create new']";
    private String fieldNameNewReadingList = "org.wikipedia:id/text_input";
    private String buttonOk="android:id/button1";
    private String backButton = "//android.widget.ImageButton[@content-desc='Navigate up']";
    private String сreatedReadingList ="//android.widget.TextView[@text='Saved articles']";
    private String buttonSavedList = "org.wikipedia:id/nav_tab_reading_lists";
    private String articlesTitile = "//android.view.View[@bounds='[42,821][913,908]']";
    private String testTitile = "//android.view.View[@bounds='[42,821][913,994]']";








    @Test
    public void savingArticles() {
        Helper helper = new Helper(driver);
        helper.elementClick(By.id(buttonSkip));
        helper.elementClick(By.id(searchBox));
        helper.enteringAValue(By.id(searchText),"Java");
        helper.elementClick(By.xpath(serchResultOne));
        helper.elementClick(By.id(buttonSave));
        helper.elementClick(By.id(buttonSave));
        helper.elementClick(By.xpath(buttonAnotherReadingList));
        helper.elementClick(By.xpath(createNewReadingList));
        helper.enteringAValue(By.id(fieldNameNewReadingList),"Saved articles");
        helper.elementClick(By.id(buttonOk));
        helper.elementClick(By.xpath(backButton));
        helper.elementClick(By.xpath(serchResultTwo));
        helper.elementClick(By.id(buttonSave));
        helper.elementClick(By.id(buttonSave));
        helper.elementClick(By.xpath(buttonAnotherReadingList));
        helper.elementClick(By.xpath(сreatedReadingList));
        helper.elementClick(By.xpath(backButton));
        helper.elementClick(By.xpath(backButton));
        helper.elementClick(By.id(buttonSavedList));
        helper.elementClick(By.xpath(сreatedReadingList));
        helper.swipeElementToLeft(By.xpath(serchResultTwo),"Не удалось осуществить свайп");
        helper.assertElementNotPresent(By.xpath(serchResultTwo),"Элемент отображается на старнице");
        helper.elementVisibility(By.xpath(serchResultOne));
        helper.elementClick(By.xpath(serchResultOne));
        helper.assertElementHasText(By.xpath(articlesTitile),"JavaScript","Заголовок не соответсвует ожидаемому");
    }



    @Test
    public void assertTitile(){
        Helper helper = new Helper(driver);
        helper.elementClick(By.id(buttonSkip));
        helper.elementClick(By.id(searchBox));
        helper.enteringAValue(By.id(searchText),"Java");
        helper.elementClick(By.xpath(serchResultTwo));
        helper.assertElementPresent(By.xpath(testTitile),"Titile not found");
    }






}
