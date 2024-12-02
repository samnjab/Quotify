package usecases.comparator;

import quotify_app.data_access.ComparatorClient;
import quotify_app.data_access.exceptions.ComparatorClientException;

public class ComparatorFetchTest {
    public static void main(String[] args) {
        ComparatorClient client = new ComparatorClient();
        try {
            // Replace with a valid property ID
            String propId = "20823815";
            String response = client.fetchComparablesByPropertyId(propId);
            System.out.println("API Response: " + response);
        } catch (ComparatorClientException e) {
            System.err.println("Error fetching comparables: " + e.getMessage());
        }
    }
}