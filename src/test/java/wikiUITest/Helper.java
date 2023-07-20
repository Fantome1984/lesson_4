package wikiUITest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Helper extends UI_Test_Wiki {
    public Helper(AppiumDriver driver){this.driver = driver;}



    public WebElement elementVisibility(By locator){
        WebDriverWait wait = new WebDriverWait(driver, 15);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement elementClick(By  locator){
        WebElement element = elementVisibility(locator);
        element.click();
        return element;
    }

    public WebElement assertElementHasText(By locator, String expected,String error){
        WebElement element = elementVisibility(locator);
        String actual = element.getAttribute("text");
        Assertions.assertEquals(expected,actual,error);
        return element;
    }

    public WebElement enteringAValue(By locator, String text){
        WebElement element = elementVisibility(locator);
        element.sendKeys(text);
        return element;
    }

    public boolean waitForElementNotPresent(By locator){
        WebDriverWait wait = new WebDriverWait(driver,15);
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void swipeUp(){
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width/2;
        int start_y = (int)(size.height*0.8);
        int end_y = (int)(size.height*0.2);
        action.press(PointOption.point(x,start_y)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(200))).
                moveTo(PointOption.point(x,end_y)).release().perform();
    }

    public void quickSwipeUp(){
        swipeUp();

    }

    public void swipeUpToElement(By by,String error_messege,int max_swipes){

       int alredy_swipe = 0;
        while (driver.findElements(by).size()==0){
            if(alredy_swipe>max_swipes){
                waitForElementNotPresent(by);
                return;
            }
            quickSwipeUp();
            ++alredy_swipe;
        }

    }

    public void swipeElementToLeft(By by, String error_messege){

        WebElement element = elementVisibility(by);
        int left_x = element.getLocation().getX();
        int rigth_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y+element.getSize().getHeight();
        int middle_y =(upper_y+lower_y)/2;
        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(rigth_x,middle_y)).
                waitAction(WaitOptions.waitOptions(Duration.ofMillis(150)))
                .moveTo(PointOption.point(left_x,middle_y)).release()
                .perform();
    }

    public int chekAmmountElement(By by){
        List elements = driver.findElements(by);
        return elements.size();
    }

    public void assertElementNotPresent(By by,String error_messege){
        int ammount_of_elements = chekAmmountElement(by);
        if (ammount_of_elements >0){
            String defaultErrorMessege ="An Element "  +by.toString()+" supposted be not present ";
            throw new AssertionError(defaultErrorMessege + " " + error_messege);
        }

    }

    public void assertElementPresent(By by, String error_messege){
        int ammount_of_elements = chekAmmountElement(by);
      Assertions.assertTrue(ammount_of_elements>0,error_messege);

    }




    private AppiumDriver driver;
}

