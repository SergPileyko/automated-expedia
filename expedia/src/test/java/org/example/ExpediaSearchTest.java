package org.example;

import org.example.pages.ExpediaSearchResultPage;
import org.example.pages.ExpediaFlightsPage;
import org.junit.jupiter.api.Test;

import static org.example.constants.TestConstants.EXPEDIA_FLIGHTS_URL;
import static org.example.constants.TestConstants.FLYING_FROM_EXPECTED_RESULT;
import static org.example.constants.TestConstants.FLYING_TO_EXPECTED_RESULT;
import static org.example.constants.TestConstants.GOING_TO_AIRPORT_CODE;
import static org.example.constants.TestConstants.LEAVING_FROM_AIRPORT_CODE;
import static org.example.constants.TestConstants.RECOMMENDED_DEPARTING_FLIGHTS;
import static org.example.constants.TestConstants.RECOMMENDED_RETURNING_FLIGHTS;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExpediaSearchTest extends BaseTest {

    private ExpediaFlightsPage expediaFlightsPage;
    private ExpediaSearchResultPage expediaSearchResultPage;

    @Test
    public void fillInFormAndSearch() {

        driver.get(EXPEDIA_FLIGHTS_URL);
        expediaFlightsPage = new ExpediaFlightsPage(driver);
        
        expediaFlightsPage
                .inputDepartureAirport(LEAVING_FROM_AIRPORT_CODE)
                .inputGoingToAirport(GOING_TO_AIRPORT_CODE)
                .selectDates()
                .addTravelers();

        String expectedDepartingDate = expediaFlightsPage.getDates()[0];
        String expectedReturningDate = expediaFlightsPage.getDates()[1];

        expediaSearchResultPage = expediaFlightsPage.clickSearchButton();

        String actualFlyingFrom = expediaSearchResultPage.getInformationFlyingForm();
        String actualFlyingTo = expediaSearchResultPage.getInformationFlyingTo();
        String actualDepartingDate = expediaSearchResultPage.getInformationDeparting();
        String actualReturningDate = expediaSearchResultPage.getInformationReturning();
        String actualRecommendedMessage = expediaSearchResultPage.getRecommendedFlightsMessage();

        assertAll("search",
                () -> assertEquals(FLYING_FROM_EXPECTED_RESULT, actualFlyingFrom),
                () -> assertEquals(FLYING_TO_EXPECTED_RESULT, actualFlyingTo),
                () -> assertEquals(RECOMMENDED_DEPARTING_FLIGHTS, actualRecommendedMessage),
                () -> assertEquals(expectedDepartingDate, actualDepartingDate),
                () -> assertEquals(expectedReturningDate, actualReturningDate)
        );

        expediaSearchResultPage
                .pickNonstopCheckBoxIfAvailable()
                .pickFirstSearchResult()
                .clickSelectPriceButton();

        String actualFlyDirection = expediaSearchResultPage.getFlyDirection();
        String actualReturningMessage = expediaSearchResultPage.getRecommendedFlightsMessage();

        expediaSearchResultPage.pickNonstopCheckBoxIfAvailable();

        assertAll("search",
                () -> assertEquals(LEAVING_FROM_AIRPORT_CODE + GOING_TO_AIRPORT_CODE, actualFlyDirection),
                () -> assertTrue(expediaSearchResultPage.isNonstopFilterVisible()),
                () -> assertEquals(RECOMMENDED_RETURNING_FLIGHTS, actualReturningMessage)
        );
    }
}
