package framework.tests;

import framework.driver.DriverUtils;
import framework.driver.UrlUtils;

import framework.utils.TestListener;
import lombok.extern.log4j.Log4j2;
import framework.models.GoogleCloudMainForm;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import framework.pages.GoogleCloudHomePage;
import framework.services.GoogleCloudMainFormCompleting;
import org.testng.annotations.Listeners;

@Log4j2
@Listeners({TestListener.class})
public abstract class BaseTest {
    protected WebDriver driver;
    protected GoogleCloudMainForm googleCloudMainForm = GoogleCloudMainFormCompleting.completeForm();


    @BeforeMethod()
    public void browserSetup() {
        driver = DriverUtils.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        log.info("Closing browser");
        DriverUtils.quit();
    }

    protected GoogleCloudHomePage loadApplication() {
        DriverUtils.getUrl(UrlUtils.getBaseUrl());
        return new GoogleCloudHomePage(driver);
    }
}

