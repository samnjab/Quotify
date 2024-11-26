package quotify_app.usecases.landing;

import java.util.ArrayList;
import java.util.List;

import quotify_app.entities.regionEntities.Address;
import quotify_app.entities.regionEntities.Area;

/**
 * The Landing Interactor.
 */
public class LandingInteractor implements LandingInputBoundary {
    private final AreaDataAccessInterface areaDataAccessObject;
    private final LandingOutputBoundary landingPresenter;

    public LandingInteractor(AreaDataAccessInterface areaDataAccessInterface,
                             LandingOutputBoundary landingOutputBoundary) {
        this.areaDataAccessObject = areaDataAccessInterface;
        this.landingPresenter = landingOutputBoundary;
    }

    /**
     * Fetches and calls presenter with a list of available countries for the user to select.
     * @param geoIdV4 the geoId of the selected country.
     */
    @Override
    public void fetchAreas(String geoIdV4) {
        try {
            final List<Area> areaList = areaDataAccessObject.getSubAreas(geoIdV4);
            final String areaType = areaList.get(0).getType();
            final AreaListOutputData outputData = new AreaListOutputData(areaList, areaType, false);
            landingPresenter.prepareAreaListSuccessView(outputData);
        }
        catch (Exception e) {
            landingPresenter.prepareAreaListFailView("Failed to fetch countries: " + e.getMessage());
        }
    }

    /**
     * Fetches and calls presenter with the selected Area.
     * @param geoIdV4 the Area object of the selected area.
     */
    @Override
    public void selectArea(String geoIdV4) {
        try {
            final Area area = areaDataAccessObject.getArea(geoIdV4);
            final AreaOutputData outputData = new AreaOutputData(area, false);
            landingPresenter.prepareAreaSuccessView(outputData);
        }
        catch (Exception e) {
            landingPresenter.prepareAreaFailView("Failed to fetch countries: " + e.getMessage());
        }
    }

    @Override
    public void selectAddress(AddressInputData addressInputData) throws Exception {
        final Area country = addressInputData.getCountry();
        final Area state = addressInputData.getState();
        final Area city = addressInputData.getCity();
        final Area zipCode = addressInputData.getZipCode();
        final Address address = addressInputData.constructAddress();
    }

    @Override
    public List<Area> autoCompleteByName(String partialName, String type) {
        try {
            if (partialName == null || partialName.isEmpty()) {
                throw new IllegalArgumentException("Partial name cannot be null or empty");
            }

            if (type == null || type.isEmpty()) {
                throw new IllegalArgumentException("Type cannot be null or empty");
            }

            final List<Area> matchedAreas = areaDataAccessObject.findAreasByNameAndType(partialName, type);

            if (matchedAreas.isEmpty()) {
                landingPresenter.prepareAreaListFailView("No matches found for: " + partialName);
            } else {
                final AreaListOutputData outputData = new AreaListOutputData(matchedAreas, type, false);
                landingPresenter.prepareAreaListSuccessView(outputData);
            }

            return matchedAreas;
        }
        catch (Exception e) {
            landingPresenter.prepareAreaListFailView("Failed to autocomplete areas: " + e.getMessage());
            return new ArrayList<>();
        }
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
