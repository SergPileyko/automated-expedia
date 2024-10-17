package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.example.logic.WaitsForPage.waitForElementLocatedByBeClickable;
import static org.example.logic.WaitsForPage.waitForPresenceElementLocatedBy;

public class ExpediaFlightsPage extends BasePage {
    private String testDepartureDate;
    private WebElement pickDepartureAirportButton;
    private WebElement goingToField;
    private WebElement pickArriveAirportButton;
    private WebElement departureDate;
    private WebElement returnDate;
    private WebElement plusAdultButton;

    @FindBy(xpath = "//div[@class='uitk-input-swapper-start-input']")
    private WebElement leavingFromArea;

    @FindBy(xpath = "//*[@id='origin_select']")
    private WebElement leavingFromField;

    @FindBy(xpath = "//div[@class='uitk-input-swapper-end-input']")
    private WebElement goingToArea;

    @FindBy(xpath = "//*[@id='date_form_field-btn']")
    private WebElement datesField;

    @FindBy(xpath = "//button[contains(@aria-label,'Save changes and close the date picker')]")
    private WebElement doneDateButton;

    @FindBy(xpath = "//button[contains(@aria-label,'Travelers')]")
    private WebElement travelerField;

    @FindBy(xpath = "//*[@id='search_button']")
    private WebElement searchButton;


    public ExpediaFlightsPage(WebDriver driver) {
        super(driver);
    }

    public ExpediaFlightsPage inputDepartureAirport(String airportCode) {
        leavingFromArea.click();
        leavingFromField.sendKeys(airportCode);
        pickDepartureAirportButton = waitForPresenceElementLocatedBy(driver,
                By.xpath("//button[contains(@aria-label,'MCO - Orlando')]"));
        pickDepartureAirportButton.click();
        return this;
    }

    public ExpediaFlightsPage inputGoingToAirport(String airportCode) {
        goingToArea.click();
        goingToField = waitForElementLocatedByBeClickable(driver,
                By.xpath("//input[@id='destination_select']"));
        goingToField.sendKeys(airportCode);
        pickArriveAirportButton = waitForPresenceElementLocatedBy(driver,
                By.xpath("//button[contains(@aria-label,'New York (JFK')]"));
        pickArriveAirportButton.click();
        return this;
    }

    public ExpediaFlightsPage selectDates() {
        datesField.click();
        departureDate = waitForPresenceElementLocatedBy(driver,
                By.xpath("(//button[@class='uitk-date-picker-day undefined'])[10]"));
        departureDate.click();
        testDepartureDate = departureDate.getText();
        returnDate = waitForPresenceElementLocatedBy(driver,
                By.xpath("(//button[@class='uitk-date-picker-day undefined selected'])[4]"));;
        returnDate.click();
        doneDateButton.click();
        return this;
    }

    public ExpediaFlightsPage addTravelers() {
        travelerField.click();
        plusAdultButton = waitForElementLocatedByBeClickable(driver,
                By.xpath("//*[@id='traveler_selector_adult_step_input-increase-title']/ancestor::button"));
        plusAdultButton.click();
        WebElement element = waitForPresenceElementLocatedBy(driver,
                By.xpath("//button[contains(@aria-label,'2 travelers')]"));
        return this;
    }

    public ExpediaSearchResultPage clickSearchButton(){

        searchButton.click();
        return new ExpediaSearchResultPage(driver);
    }

    public String [] getDates(){
        WebElement element = waitForPresenceElementLocatedBy(driver,
                By.xpath("//button[@data-name='startDate']"));
        return element.getText().split("\\s-\\s");
    }
}
