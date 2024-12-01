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
            final Address address = addressInputData.constructAddress();
            final Property property = propertyDataAccessObject.getPropertyAtAddress(address);
            propertyDataAccessObject.setCurrentProperty(property);
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
