package frameworks.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import frameworks.pages.EstimatePreviewPage;
import frameworks.pages.GoogleCloudHomePage;
import frameworks.pages.GoogleCloudPricingCalculatorPage;

import java.util.List;
import java.util.Map;

public class SmokeTests extends BaseTest {
    private String originalWindow;
    private Double totalMonthlyPriceFromForm;

    @Test(description = "Check total price")
    public void mainFormCompletingTest() {
        loadApplication();

        GoogleCloudPricingCalculatorPage actualPricingResults = new GoogleCloudHomePage(driver)
                .openPricingLink()
                .openPricingCalculator()
                .calculatePrice(googleCloudMainForm)
                .clickShareButton();
        totalMonthlyPriceFromForm = actualPricingResults.getTotalMonthlyPrice();
        originalWindow = driver.getWindowHandle();
        actualPricingResults.switchEstimatePreviewPage();

        EstimatePreviewPage estimatePreviewPage = new EstimatePreviewPage(driver);
        estimatePreviewPage.switchNewWindows(originalWindow);
        List<String> listOfItems = estimatePreviewPage.getCalculatedValueFromForm();

        Assert.assertEquals(totalMonthlyPriceFromForm, estimatePreviewPage.getTotalEstimate(listOfItems),
                "Issue in Total Estimated Cost: actual result is \n" + totalMonthlyPriceFromForm);
    }
}


