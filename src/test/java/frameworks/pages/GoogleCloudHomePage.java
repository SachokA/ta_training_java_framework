package frameworks.pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
@Log4j2
public class GoogleCloudHomePage extends AbstractPage {
    @FindBy(xpath = "//a[contains(text(),'Pricing')]")
    private WebElement pricingLink;
    @FindBy(xpath = "//div[contains(text(),'Pricing calculator')]")
    private WebElement pricingCalculatorLink;

    public GoogleCloudHomePage(WebDriver driver) {
        super(driver);
    }

     public GoogleCloudHomePage openPricingLink() {
        log.info("Clicking in header link pricing");
        pricingLink.click();
        return this;
    }

    public GoogleCloudPricingCalculatorPage openPricingCalculator() {
        log.info("Clicking in calculator pricing link");
        pricingCalculatorLink.click();
        return new GoogleCloudPricingCalculatorPage(driver);
    }
}
