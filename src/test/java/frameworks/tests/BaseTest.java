package frameworks.tests;

import frameworks.driver.DriverUtils;
import frameworks.driver.UrlUtils;

import frameworks.utils.TestListener;
import lombok.extern.log4j.Log4j2;
import frameworks.models.CalculatedForm;
import frameworks.models.GoogleCloudMainForm;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import frameworks.pages.GoogleCloudHomePage;
import frameworks.services.GoogleCloudMainFormCompleting;
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

