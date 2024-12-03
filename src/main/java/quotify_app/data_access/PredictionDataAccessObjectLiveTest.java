package quotify_app.data_access;

import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import quotify_app.data_access.exceptions.PredictionClientException;
import quotify_app.entities.PredictionRequest;
import quotify_app.entities.regionEntities.Address;
import quotify_app.entities.regionEntities.Identifier;
import quotify_app.entities.regionEntities.Property;
import quotify_app.entities.regionEntities.Summary;

/**
 * Test class for the PredictionDataAccessObject.
 */
class PredictionDataAccessObjectLiveTest {
    private PredictionDataAccessObject dataAccessObject;
    private final int numPredictions = 6;
    private final Address address = new Address(
            "US",
            "CA",
            "San Francisco",
            "Market St",
            "123",
            "94103"
    );
    private final Identifier identifier = new Identifier(
            "123456",
            Map.of(
                    "ZI", "00b6f7372d30b75c08e761bf9f975fa4",
                    "CS", "123",
                    "ST", "CA",
                    "CO", "SomeCounty",
                    "N2", "Neighborhood"
            )
    );
    private final Summary summary = new Summary(
            "SFR",
            3,
            2,
            "Good",
            1,
            1500,
            2000
    );
    private final Property property = new Property(identifier, address, summary);

    @BeforeEach
    void setUp() {
        // Initialize the real PredictionClient and DataAccessObject
        final PredictionClient client = new PredictionClient();
        dataAccessObject = new PredictionDataAccessObject(client);
    }

    @Test
    void testGetCurrentPricePredictionPass() throws PredictionClientException {
        // Mock property data

        // Call the live API for current price prediction
        final double prediction = dataAccessObject.getCurrentPricePrediction(property);

        // Assert that the prediction is a valid numerical value
        Assertions.assertTrue(prediction > 0, "Prediction should be a positive number");
        System.out.println("Current Price Prediction: " + prediction);

    }

    @Test
    void testGetFuturePricePredictionsPass() throws PredictionClientException {

        // Call the live API for future price predictions
        final double[] futurePredictions = dataAccessObject.getFuturePricePredictions(property);

        // Assert that the predictions array has 6 valid numerical values
        Assertions.assertEquals(numPredictions, futurePredictions.length, "There should be exactly 6 predictions");
        for (double prediction : futurePredictions) {
            Assertions.assertTrue(prediction > 0, "Each prediction should be a positive number");
        }
        System.out.println("Future Price Predictions: ");
        for (double prediction : futurePredictions) {
            System.out.println(prediction);
        }

    }

    @Test
    void testGetCurrentPricePredictionFail() {
        // Mock property data

        try {
            // Simulate an exception by creating a mock PredictionClient
            final PredictionClient mockClient = new PredictionClient() {
                @Override
                public String predict(PredictionRequest request) throws PredictionClientException {
                    throw new PredictionClientException("Simulated exception in predict()");
                }
            };

            // Inject the mock PredictionClient into PredictionDataAccessObject
            final PredictionDataAccessObject mockDataAccessObject = new PredictionDataAccessObject(mockClient);

            // This should throw an exception
            mockDataAccessObject.getCurrentPricePrediction(property);
        }
        catch (PredictionClientException err) {
            // Verify that the exception was correctly caught
            Assertions.assertTrue(err.getMessage().contains("Simulated exception"),
                    "Unexpected exception message: " + err.getMessage());
            System.out.println("Caught expected exception: " + err.getMessage());
        }
    }

    @Test
    void testGetFuturePricePredictionsFail() {
        // Mock property data

        try {
            // Simulate an exception by creating a mock PredictionClient
            final PredictionClient mockClient = new PredictionClient() {
                @Override
                public String predict(PredictionRequest request) throws PredictionClientException {
                    throw new PredictionClientException("Simulated exception in predict()");
                }
            };

            // Inject the mock PredictionClient into PredictionDataAccessObject
            final PredictionDataAccessObject mockDataAccessObject = new PredictionDataAccessObject(mockClient);

            // This should throw an exception
            mockDataAccessObject.getFuturePricePredictions(property);
        }
        catch (PredictionClientException err) {
            // Verify that the exception was correctly caught
            Assertions.assertTrue(err.getMessage().contains("Simulated exception"),
                    "Unexpected exception message: " + err.getMessage());
            System.out.println("Caught expected exception: " + err.getMessage());
        }
    }

    @Test
    void testGetCurrentPricePredictionString() {
        // Mock a successful current price prediction

        try {
            // Call the live API to populate current price prediction
            dataAccessObject.getCurrentPricePrediction(property);

            // Fetch the current price prediction
            final double currentPrice = dataAccessObject.getCurrentPricePredictionString();

            // Assert that the returned value matches the expected range
            Assertions.assertTrue(currentPrice > 0, "Current price prediction should be a positive value");
            System.out.println("Retrieved Current Price Prediction: " + currentPrice);
        }
        catch (PredictionClientException err) {
            Assertions.fail("Prediction failed: " + err.getMessage());
        }
    }

    @Test
    void testGetFuturePredictionsArray() {
        // Mock a successful future price prediction
        try {
            // Call the live API to populate future price predictions
            dataAccessObject.getFuturePricePredictions(property);

            // Fetch the future predictions array
            final double[] futurePrices = dataAccessObject.getFuturePricePredictions(property);

            // Assert that the array has 6 elements, all positive
            Assertions.assertEquals(numPredictions, futurePrices.length, "The predictions array should have 6 values");
            for (double price : futurePrices) {
                Assertions.assertTrue(price > 0, "Each future price prediction should be a positive value");
            }

            System.out.println("Retrieved Future Price Predictions: ");
            for (double price : futurePrices) {
                System.out.println(price);
            }
        }
        catch (PredictionClientException err) {
            Assertions.fail("Prediction failed: " + err.getMessage());
        }
    }

}

