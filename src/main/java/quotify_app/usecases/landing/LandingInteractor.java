package quotify_app.usecases.landing;

import java.util.List;

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

    /**
     * Fetches, caches and calls presenter with a list of available areas for the user to select.
     * @param geoIdV4 the geoId of the selected parent area.
     * @param type the type of subarea to be fetched.
     */
    @Override
    public void fetchAreas(String geoIdV4, String type) {
        try {
            final List<Area> areaList = areaDataAccessObject.getSubAreas(geoIdV4, type);
            areaDataAccessObject.cacheAreas(areaList, type);
            final AreaListOutputData outputData = new AreaListOutputData(areaList, type, false);
            landingPresenter.prepareAreaListSuccessView(outputData);
        }
        catch (Exception e) {
            landingPresenter.prepareAreaListFailView("Failed to fetch countries: " + e.getMessage());
        }
    }

    /**
     * Selects the area and calls presenter with the selected area.
     * @param area the Area object to be selected.
     * @return Area the selected area.
     */
    @Override
    public void selectArea(Area area) {
        areaDataAccessObject.selectArea(area);
        final AreaOutputData outputData = new AreaOutputData(area, false);
        landingPresenter.prepareAreaSuccessView(outputData);
    }

    @Override
    public void autoCompleteByName(String partialName, String type) {
        if (partialName == null || partialName.isEmpty() || type == null || type.isEmpty()) {
            landingPresenter.prepareAreaListFailView("No matches found for: " + partialName);
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

    /**
     * Trigger view transition to Signup through the presenter.
     */
    @Override
    public void goToSignup() {
        landingPresenter.goToSignup();
    }

    /**
     * Trigger view transition to Login through the presenter.
     */
    @Override
    public void goToLogin() {
        landingPresenter.goToLogin();
    }

}
