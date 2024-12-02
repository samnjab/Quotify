package quotify_app.data_access;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import quotify_app.data_access.exceptions.ApiRequestException;
import quotify_app.data_access.exceptions.ClientRequestException;
import quotify_app.entities.regionEntities.Area;
import quotify_app.entities.regionEntities.Property;
import quotify_app.entities.regionEntities.Summary;
import quotify_app.entities.regionEntities.Identifier;
import quotify_app.entities.regionEntities.Address;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the ComparatorDataAccessObject.
 */
public class ComparatorDataAccessObjectTest {

    private ComparatorDataAccessObject dataAccessObject;
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
        final ComparatorClient client = new ComparatorClient();
        dataAccessObject = new ComparatorDataAccessObject();
    }


    @Test
    void testGetSaleComparablesPass() throws ApiRequestException, ClientRequestException {
        // Set up property data for testing


        dataAccessObject.setCurrentProperty(property);

        // Use a clean zip code without any prefix
        Area zipcode = new Area("94103");


        // Call getSaleComparables and verify the response
        List<Property> result = dataAccessObject.getSaleComparables(zipcode);

        // Verify that the result contains three properties
        assertEquals(3, result.size(), "The result should contain exactly three properties.");
    }

}
