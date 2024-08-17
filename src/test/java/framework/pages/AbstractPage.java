package framework.pages;

import framework.driver.DriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public abstract class AbstractPage {
    protected WebDriver driver;
    protected final int WAIT_TIMEOUT_SECONDS = 10;
;
    protected AbstractPage(WebDriver driver) {
        this.driver = DriverUtils.getDriver();
        PageFactory.initElements(driver, this);
    }
    protected WebDriverWait getNewWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS));
    }
    protected WebElement waitPresenceOfElementLocated(By locator) {
        return getNewWait().until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    public void waitWebElementVisibilityOf(WebElement element) {
        getNewWait().until(ExpectedConditions.visibilityOf(element));
    }


    protected void waitInvisibilityOf(WebElement element) {
        getNewWait().until(ExpectedConditions.invisibilityOf(element));
    }

    protected WebElement waitElementToBeClickable(WebElement element) {
        return getNewWait()
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void selectDropdownOption(String dropdownOptionsXpath, String option) {
        WebElement dropdownOption =
                waitPresenceOfElementLocated(By.xpath(String.format(dropdownOptionsXpath, option)));
        dropdownOption.click();
        waitInvisibilityOf(dropdownOption);
    }
    protected void waitWebElementVisibilityOfAll(List<WebElement> elementList) {
         getNewWait().until(ExpectedConditions.visibilityOfAllElements(elementList));
    }
    public void switchNewWindows(String originalWindow) {
        getNewWait().until(ExpectedConditions.numberOfWindowsToBe(2));
        Set<String> allWindows = driver.getWindowHandles();

        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    void jsScrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
    void jsClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }
    public static void waitForSecond(Duration sec) {
        try {
            Thread.sleep(sec.toMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
