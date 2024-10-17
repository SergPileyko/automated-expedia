package org.example.logic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.example.constants.TestConstants.DURATION_OF_SECOND;

public class WaitsForPage {
    public static WebElement waitForPresenceElementLocatedBy(WebDriver driver, By by) {
        return new WebDriverWait(driver, Duration.ofSeconds(DURATION_OF_SECOND))
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static WebElement waitForElementLocatedByBeClickable(WebDriver driver, By by) {
        return new WebDriverWait(driver, Duration.ofSeconds(DURATION_OF_SECOND))
                .until(ExpectedConditions.elementToBeClickable(by));
    }
}
