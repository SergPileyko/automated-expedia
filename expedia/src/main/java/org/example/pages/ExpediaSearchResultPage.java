package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.example.logic.WaitsForPage.waitForPresenceElementLocatedBy;

public class ExpediaSearchResultPage extends BasePage {
    private WebElement flyDirection;
    private WebElement recommendedFlights;
    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    @FindBy(xpath = "//button[contains(@aria-label,'Flying from')]")
    private WebElement flyingFromArea;

    @FindBy(xpath = "//button[contains(@aria-label,'Flying to')]")
    private WebElement flyingToField;

    @FindBy(xpath = "//button[contains(@aria-label,'Departing')]")
    private WebElement departingField;

    @FindBy(xpath = "//button[contains(@aria-label,'Returning')]")
    private WebElement returningField;

    @FindBy(xpath = "//*[@id='Nonstop']")
    private WebElement nonstopFilter;

    public ExpediaSearchResultPage(WebDriver driver) {
        super(driver);
    }

    public String getInformationFlyingForm() {
        return flyingFromArea.getText();
    }

    public String getInformationFlyingTo() {
        return flyingToField.getText();
    }

    public String getInformationDeparting() {
        return departingField.getText();
    }

    public String getInformationReturning() {
        return returningField.getText();
    }

    public String getRecommendedFlightsMessage() {
        recommendedFlights = waitForPresenceElementLocatedBy(driver,
                By.xpath("//*[contains(@data-test-id,'listings-header-recommended')]"));
        return recommendedFlights.getText();
    }

    public ExpediaSearchResultPage pickNonstopCheckBoxIfAvailable() {
        List<WebElement> nonstopCheckBox = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//input[contains(@id,'NUM_OF_STOPS-0')]/../div")));

        if (!nonstopCheckBox.isEmpty()) {
            WebElement checkBox = nonstopCheckBox.get(0);
            checkBox.click();
        }
        return this;
    }

    public ExpediaSearchResultPage pickFirstSearchResult() {

        List<WebElement> searchResults = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//div[@data-test-id='intersection-observer']")));
        if (!searchResults.isEmpty()) {
            WebElement result = wait.until(ExpectedConditions.elementToBeClickable(searchResults.get(0)));
            result.click();
        }
        return this;
    }

    public ExpediaSearchResultPage clickSelectPriceButton() {

        List<WebElement> searchResults = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//button[@data-test-id='select-button']")));
        if (!searchResults.isEmpty()) {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(searchResults.get(0)));
            button.click();
        }
        return this;
    }

    public boolean isNonstopFilterVisible() {
        return nonstopFilter.isDisplayed();
    }

    public String getFlyDirection() {

        flyDirection = waitForPresenceElementLocatedBy(driver,
                By.xpath("//*[@class='uitk-progress-indicator-details-texts']"));
        return flyDirection.getText().replaceAll("[\\W]", "");
    }
}
