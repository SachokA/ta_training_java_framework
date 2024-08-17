package frameworks.tests;

import frameworks.models.CalculatedForm;
import frameworks.pages.EstimatePreviewPage;
import frameworks.pages.GoogleCloudHomePage;
import frameworks.pages.GoogleCloudPricingCalculatorPage;
import org.assertj.core.api.SoftAssertions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Map;

import static frameworks.services.CreateCalculatedForm.completeCalculatedForm;

public class CheckItemsTest extends BaseTest {
    private String originalWindow;

    @Test(description = "Check items")
    public void mainFormCompletingTest() {
        loadApplication();

        GoogleCloudPricingCalculatorPage actualPricingResults = new GoogleCloudHomePage(driver)
                .openPricingLink()
                .openPricingCalculator()
                .calculatePrice(googleCloudMainForm)
                .clickShareButton();

        originalWindow = driver.getWindowHandle();
        actualPricingResults.switchEstimatePreviewPage();

        EstimatePreviewPage estimatePreviewPage = new EstimatePreviewPage(driver);
        estimatePreviewPage.switchNewWindows(originalWindow);
       ;
        Map<String, String> listOfItems = estimatePreviewPage.getItemsFromForm();
        System.out.println(listOfItems);
        CalculatedForm form = completeCalculatedForm(listOfItems);
        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(form.getDatacenterLocation())
                .as("Datacenter Location")
                .containsIgnoringCase(googleCloudMainForm.getDatacenterLocation())
                .withFailMessage("Datacenter Location does not contain the expected location. Expected: %s, but was: %s",
                        googleCloudMainForm.getDatacenterLocation(), form.getDatacenterLocation());

        softly.assertThat(form.getLocalSSD())
                .as("Local SSD")
                .containsIgnoringCase(googleCloudMainForm.getLocalSSD())
                .withFailMessage("Local SSD does not contain the expected SSD type. Expected: %s, but was: %s",
                        googleCloudMainForm.getLocalSSD(), form.getLocalSSD());

        softly.assertThat(form.getOperationSystem().toLowerCase())
                .as("Operating System")
                .startsWith(googleCloudMainForm.getOperationSystem().toLowerCase())
                .withFailMessage("Operating System does not contain the expected system. Expected: %s, but was: %s",
                        googleCloudMainForm.getOperationSystem(), form.getOperationSystem());
        softly.assertAll();
    }
}
