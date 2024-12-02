package quotify_app.usecases.landing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import quotify_app.data_access.exceptions.ApiRequestException;
import quotify_app.data_access.exceptions.ClientRequestException;
import quotify_app.data_access.exceptions.IllegalTypeException;
import quotify_app.entities.regionEntities.Address;
import quotify_app.entities.regionEntities.Area;
import quotify_app.entities.regionEntities.Property;
import quotify_app.entities.regionEntities.Summary;

/**
 * The Landing Interactor.
 */
public class LandingInteractor implements LandingInputBoundary {
    private final AreaDataAccessInterface areaDataAccessObject;
    private final PropertyDataAccessInterface propertyDataAccessObject;
    private final LandingOutputBoundary landingPresenter;
    private final Map<String, String> areaTypeHierarchy = new HashMap<>();

    public LandingInteractor(AreaDataAccessInterface areaDataAccessInterface,
                             PropertyDataAccessInterface propertyDataAccessInterface,
                             LandingOutputBoundary landingOutputBoundary) {
        this.areaDataAccessObject = areaDataAccessInterface;
        this.propertyDataAccessObject = propertyDataAccessInterface;
        this.landingPresenter = landingOutputBoundary;
        this.areaTypeHierarchy.put("CN", "ST");
        this.areaTypeHierarchy.put("ST", "CS");
        this.areaTypeHierarchy.put("CS", "ZI");
    }

    // Helper methods:
    private Map<String, String> constructPropDetails(Summary summary) {
        final Map<String, String> propDetails = new HashMap<>();
        propDetails.put("Property Type", summary.getPropTypeString());
        propDetails.put("Condition", summary.getConditionString());
        propDetails.put("Bedrooms", String.valueOf(summary.getBeds()));
        propDetails.put("Bathrooms", String.valueOf(summary.getBaths()));
        propDetails.put("Year Built", String.valueOf(summary.getYearBuilt()));
        propDetails.put("Size (sqft)", String.valueOf(summary.getSize()));
        propDetails.put("Number of levels", String.valueOf(summary.getLevels()));

        return propDetails;
    }

    // Override methods:
    @Override
    public void fetchCountries() {
        try {
            final List<Area> countries = areaDataAccessObject.getCountries();
            areaDataAccessObject.cacheAreas(countries, "CN");
            final AreaListOutputData outputData = areaDataAccessObject.getCache().getSubAreaList("CN");
            landingPresenter.prepareAreaListSuccessView(outputData);
        }
        catch (ApiRequestException exception) {
            landingPresenter.prepareErrorView("Failed to fetch countries due to an API error: "
                    + exception.getMessage());
        }
        catch (IllegalTypeException exception) {
            landingPresenter.prepareErrorView("Illegal type specified:" + exception.getMessage());
        }
    }

    @Override
    public void fetchAreas(String geoIdV4, String parentType) {
        final String type = areaTypeHierarchy.get(parentType);
        try {
            final List<Area> areaList = areaDataAccessObject.getSubAreas(geoIdV4, type);
            areaDataAccessObject.cacheAreas(areaList, type);
            final AreaListOutputData outputData = areaDataAccessObject.getCache().getSubAreaList(type);
            landingPresenter.prepareAreaListSuccessView(outputData);
        }
        catch (ClientRequestException exception) {
            landingPresenter.prepareErrorView("A network error occurred while fetching countries. Please try again.");
        }
        catch (ApiRequestException exception) {
            landingPresenter.prepareErrorView("Failed to fetch countries due to an API error: "
                    + exception.getMessage());
        }
        catch (IllegalTypeException exception) {
            landingPresenter.prepareErrorView("Illegal type specified:" + type + exception.getMessage());
        }
    }

    @Override
    public void selectArea(Area area) {
        areaDataAccessObject.selectArea(area);
        final AreaOutputData outputData = areaDataAccessObject.getCache().getSelectedArea(area.getType());
        landingPresenter.prepareAreaSuccessView(outputData);
    }

    @Override
    public void autoCompleteByName(String partialName, String type) {
        if (partialName == null || partialName.isEmpty() || type == null || type.isEmpty()) {
            landingPresenter.prepareAreaListFailView("Start Typing..." + partialName);
        }
        final List<Area> matchedAreas = areaDataAccessObject.findAreasByNameAndType(partialName, type);
        if (matchedAreas.isEmpty()) {
            landingPresenter.prepareAreaListFailView("No matches found for: " + partialName);
        }
        else {
            final AreaListOutputData outputData = new AreaListOutputData(matchedAreas, type, false);
            landingPresenter.prepareAreaListSuccessView(outputData);
        }
    }

    @Override
    public void selectAddress(AddressInputData addressInputData) {
        try {
            // constructing address from addressInputData:
            final Address address = addressInputData.constructAddress();
            // fetching property at address from data access:
            final Property property = propertyDataAccessObject.getPropertyAtAddress(address);
            // caching property in data access:
            propertyDataAccessObject.setCurrentProperty(property);
            // producing propertyOutputData:
            final String stringAddress = address.fetchAddress1() + address.fetchAddress2();
            final Map<String, String> propDetails = constructPropDetails(property.getSummary());
            final PropertyOutputData propertyOutputData = new PropertyOutputData(stringAddress, propDetails);
            landingPresenter.preparePropertySuccessView(propertyOutputData);
        }
        catch (ClientRequestException exception) {
            landingPresenter.prepareErrorView("A network error occurred while fetching address. Please try again.");
        }
        catch (ApiRequestException exception) {
            landingPresenter.prepareErrorView("Failed to fetch address due to an API error: "
                    + exception.getMessage());
        }
    }

    @Override
    public void goToSignup() {
        landingPresenter.goToSignup();
    }

    @Override
    public void goToLogin() {
        landingPresenter.goToLogin();
    }

}
