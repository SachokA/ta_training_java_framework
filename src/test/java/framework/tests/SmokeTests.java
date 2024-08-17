package framework.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import framework.pages.EstimatePreviewPage;
import framework.pages.GoogleCloudHomePage;
import framework.pages.GoogleCloudPricingCalculatorPage;

import java.util.List;

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


