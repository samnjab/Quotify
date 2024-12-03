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

/**
 * The Landing Interactor.
 */
public class LandingInteractor implements LandingInputBoundary {
    private final AreaDataAccessInterface areaDataAccessObject;
    private final PropertyDataAccessInterface propertyDataAccessObject;
    private final LandingOutputBoundary landingPresenter;
    private Property currentProperty;
    private final Map<String, String> areaTypeHierarchy = new HashMap<>();

    public LandingInteractor(AreaDataAccessInterface areaDataAccessInterface,
                             PropertyDataAccessInterface propertyDataAccessInterface,
                             LandingOutputBoundary landingOutputBoundary) {
        this.areaDataAccessObject = areaDataAccessInterface;
        this.propertyDataAccessObject = propertyDataAccessInterface;
        this.landingPresenter = landingOutputBoundary;
        this.currentProperty = new Property();
        this.areaTypeHierarchy.put("CN", "ST");
        this.areaTypeHierarchy.put("ST", "CS");
        this.areaTypeHierarchy.put("CS", "ZI");
    }

    // Override methods:
    @Override
    public void fetchCountries() {
        try {
            final List<Area> countries = areaDataAccessObject.getCountries();
            areaDataAccessObject.cacheAreas(countries, "CN");
            final AreaListDataTransferObj outputData = new AreaListDataTransferObj(countries, "CN", false);
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
            final AreaListDataTransferObj outputData = new AreaListDataTransferObj(areaList, type, false);
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
    public void selectArea(AreaDataTransferObj areaDto) {
        areaDataAccessObject.selectArea(areaDto);
        landingPresenter.prepareAreaSuccessView(areaDto);
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
            final AreaListDataTransferObj outputData = new AreaListDataTransferObj(matchedAreas, type, false);
            landingPresenter.prepareAreaListSuccessView(outputData);
        }
    }

    @Override
    public void selectAddress(AddressDataTransferObj addressDataTransferObj) {
        try {
            // constructing address from addressInputData:
            final Address address = addressDataTransferObj.constructAddress();
            // fetching property at address from data access:
            final Property property = propertyDataAccessObject.getPropertyAtAddress(address);
            // set currentProperty for caching later:
            this.currentProperty = property;
            // producing propertyOutputData:
            final PropertyOutputData propertyOutputData = new PropertyOutputData(address, property);
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
    public void selectPropertyInCache() {
        // caching property in data access as current property:
        propertyDataAccessObject.setCurrentProperty(currentProperty);
        landingPresenter.prepareNextPageNavigation();
    }

    @Override
    public void goToSignup() {
        landingPresenter.goToSignup();
    }

    @Override
    public void goToLogin() {
        landingPresenter.goToLogin();
    }

    @Override
    public void goToUserProfile() {
        landingPresenter.presentGoToUserProfile();
    }

    @Override
    public void checkLoginStatus() {
        landingPresenter.updateLoginStatus();
    }

}
