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
    private final Area zipCode = new Area("94103");

    @BeforeEach
    void setUp() {
        // Initialize the ComparatorDataAccessObject
        dataAccessObject = new ComparatorDataAccessObject();
    }

    @Test
    void testGetSaleComparablesPass() throws ApiRequestException, ClientRequestException {
        // Set up property data for testing
        Address address1 = new Address("", "CA", "San Francisco", "123 Market St", "", "94103");
        Identifier identifier1 = new Identifier(
                "123456",
                Map.of(
                        "ZI", "00b6f7372d30b75c08e761bf9f975fa4",
                        "CS", "123",
                        "ST", "CA",
                        "CO", "SomeCounty",
                        "N2", "Neighborhood"
                )
        );
        Summary summary1 = new Summary("SFR", 3, 2, "Good", 2, 1500, 2000);
        Property property1 = new Property(identifier1, address1, summary1);

        Address address2 = new Address("", "CA", "San Francisco", "456 Market St", "", "94103");
        Identifier identifier2 = new Identifier(
                "123457",
                Map.of(
                        "ZI", "00b6f7372d30b75c08e761bf9f975fa5",
                        "CS", "124",
                        "ST", "CA",
                        "CO", "SomeCounty",
                        "N2", "Neighborhood"
                )
        );
        Summary summary2 = new Summary("SFR", 4, 3, "Good", 2, 1800, 2001);
        Property property2 = new Property(identifier2, address2, summary2);

        Address address3 = new Address("", "CA", "San Francisco", "789 Market St", "", "94103");
        Identifier identifier3 = new Identifier(
                "123458",
                Map.of(
                        "ZI", "00b6f7372d30b75c08e761bf9f975fa6",
                        "CS", "125",
                        "ST", "CA",
                        "CO", "SomeCounty",
                        "N2", "Neighborhood"
                )
        );
        Summary summary3 = new Summary("SFR", 5, 4, "Good", 3, 2000, 2002);
        Property property3 = new Property(identifier3, address3, summary3);

        dataAccessObject.setCurrentProperty(property1);

        // Use a clean zip code without any prefix
        Area cleanZipCode = new Area("94103");

        // Call getSaleComparables and verify the response
        List<Property> result = dataAccessObject.getSaleComparables(cleanZipCode);

        // Verify that the result contains three properties
        assertEquals(3, result.size(), "The result should contain exactly three properties.");
        int count = 0;
        for (Property property : result) {
            count += 1;
        }
        assertEquals(3, count, "Each list should contain exactly three properties.");
    }






}
