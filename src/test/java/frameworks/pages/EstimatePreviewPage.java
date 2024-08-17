package frameworks.pages;

import frameworks.models.CalculatedForm;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EstimatePreviewPage extends AbstractPage {

    @FindBy(css = ".SeJRAd")
    List<WebElement> resultBlockPrice;

    @FindBy(xpath = "//span[@class='Z7Pe2d g5Ano']")
    List<WebElement> resultBlockItems;

    public EstimatePreviewPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getCalculatedValueFromForm() {
        waitWebElementVisibilityOfAll(resultBlockPrice);
        List<String> calculatedFormText = new ArrayList<>();
        List<WebElement> calculatedForm = resultBlockPrice;
        for (WebElement element : calculatedForm) {
            calculatedFormText.add(element.getText());
        }
        return calculatedFormText;
    }

    public Map<String, String> getItemsFromForm() {
        waitWebElementVisibilityOfAll(resultBlockItems);
        Map<String, String> formMap = new HashMap<>();

        for (WebElement element : resultBlockItems) {

            List<WebElement> spans = element.findElements(By.tagName("span"));
            if (spans.size() >= 2) {
                String key = spans.get(0).getText();
                String value = spans.get(1).getText();
                formMap.put(key, value);
            }
        }
        return formMap;
    }
    public CalculatedForm convertToForm() {
        Map<String, String> formMap = getItemsFromForm();
        return CalculatedForm.fromMap(formMap);
    }

    public static double calculateSumExcludingLast(List<String> elements) {
        double sum = 0.0;
        if (elements.size() <= 1) {
            return sum;
        }
        for (int i = 0; i < elements.size() - 1; i++) {
            String text = elements.get(i);
            if (!text.equalsIgnoreCase("N/A")) {
                try {
                    String cleanedText = text.replaceAll("[^\\d.,]", "");

                    cleanedText = cleanedText.replace(",", "");

                    double value = Double.parseDouble(cleanedText);
                    sum += value;
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        BigDecimal bd = new BigDecimal(sum);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public double getTotalEstimate(List<String> list) {

        return calculateSumExcludingLast(list);
    }
}
