package quotify_app.usecases.landing;

import java.util.List;

import quotify_app.data_access.exceptions.ApiRequestException;
import quotify_app.data_access.exceptions.ClientRequestException;
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

    public LandingInteractor(AreaDataAccessInterface areaDataAccessInterface,
                             PropertyDataAccessInterface propertyDataAccessInterface,
                             LandingOutputBoundary landingOutputBoundary) {
        this.areaDataAccessObject = areaDataAccessInterface;
        this.propertyDataAccessObject = propertyDataAccessInterface;
        this.landingPresenter = landingOutputBoundary;
    }

    @Override
    public void fetchCountries() {
        try {
            final List<Area> countries = areaDataAccessObject.getCountries();
            areaDataAccessObject.cacheAreas(countries, "CN");
            final AreaListOutputData outputData = new AreaListOutputData(countries, "CN", false);
            landingPresenter.prepareAreaListSuccessView(outputData);
        }
        // the following exceptions may or may not be thrown by the model based version of fetchCountries
        catch (ClientRequestException exception) {
            landingPresenter.prepareErrorView("A network error occurred while fetching countries. Please try again.");
        }
        catch (ApiRequestException exception) {
            landingPresenter.prepareErrorView("Failed to fetch countries due to an API error: "
                    + exception.getMessage());
        }
    }

    @Override
    public void fetchAreas(String geoIdV4, String type) {
        try {
            final List<Area> areaList = areaDataAccessObject.getSubAreas(geoIdV4, type);
            areaDataAccessObject.cacheAreas(areaList, type);
            final AreaListOutputData outputData = new AreaListOutputData(areaList, type, false);
            landingPresenter.prepareAreaListSuccessView(outputData);
        }
        catch (ClientRequestException exception) {
            landingPresenter.prepareErrorView("A network error occurred while fetching countries. Please try again.");
        }
        catch (ApiRequestException exception) {
            landingPresenter.prepareErrorView("Failed to fetch countries due to an API error: "
                    + exception.getMessage());
        }
    }

    @Override
    public void selectArea(Area area) {
        areaDataAccessObject.selectArea(area);
        final AreaOutputData outputData = new AreaOutputData(area, false);
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
        final Address address = addressInputData.constructAddress();
        final Property property = propertyDataAccessObject.getPropertyAtAddress(address);
        propertyDataAccessObject.setCurrentProperty(property);
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
