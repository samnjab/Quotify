package quotify_app.adapters.landing;

import java.util.List;

import quotify_app.adapters.ViewManagerModel;
import quotify_app.entities.regionEntities.Address;
import quotify_app.entities.regionEntities.Area;
import quotify_app.usecases.landing.AreaListOutputData;
import quotify_app.usecases.landing.AreaOutputData;
import quotify_app.usecases.landing.LandingOutputBoundary;
import quotify_app.usecases.landing.PropertyOutputData;

/**
 * The presenter for the Landing Page use case. Prepares the data for display in the view
 * and updates the LandingViewModel accordingly.
 */
public class LandingPresenter implements LandingOutputBoundary {

    private final LandingViewModel landingViewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructs a new LandingPresenter with the specified view models.
     *
     * @param landingViewModel  The view model for managing Landing Page state.
     * @param viewManagerModel  The view model for managing view transitions.
     */
    public LandingPresenter(LandingViewModel landingViewModel, ViewManagerModel viewManagerModel) {
        this.landingViewModel = landingViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepares the view for successful area data retrieval.
     * @param areaListOutputData The output data containing the list of areas and their type.
     */
    @Override
    public void prepareAreaListSuccessView(AreaListOutputData areaListOutputData) {
        System.out.println("presenter: preparing area list success view: " + areaListOutputData);
        if (!areaListOutputData.isSelectionFailed()) {
            final List<Area> areas = areaListOutputData.getAreas();
            final String type = areaListOutputData.getAreaType();

            switch (type) {
                case "CN":
                    landingViewModel.setAvailableCountries(areas);
                    break;
                case "ST":
                    landingViewModel.setAvailableStates(areas);
                    break;
                case "CS":
                    landingViewModel.setAvailableCities(areas);
                    break;
                case "ZI":
                    landingViewModel.setAvailableZipCodes(areas);
                    break;
                default:
                    landingViewModel.setErrorMessage("Unknown area type: " + type);
                    break;
            }
        }
        else {
            landingViewModel.setErrorMessage("Failed to fetch areas.");
        }
    }

    /**
     * Prepares the view for an error in area data retrieval.
     * @param errorMessage The error message to display.
     */
    @Override
    public void prepareAreaListFailView(String errorMessage) {
        System.out.println("presenter: preparing area list fail view: ");
        landingViewModel.setErrorMessage(errorMessage);
    }

    /**
     * Prepares the view for a successful area selection.
     * @param areaOutputData The output data containing the selected area result.
     */
    @Override
    public void prepareAreaSuccessView(AreaOutputData areaOutputData) {
        System.out.println("presenter: preparing area success view: " + areaOutputData);
        if (!areaOutputData.isSelectionFailed()) {
            final Area selectedArea = areaOutputData.getArea();
            final String type = selectedArea.getType();

            switch (type) {
                case "CN":
                    landingViewModel.setAvailableStates(null);
                    landingViewModel.setAvailableCities(null);
                    landingViewModel.setAvailableZipCodes(null);
                    landingViewModel.setSelectedCountry(selectedArea);
                    break;
                case "ST":
                    landingViewModel.setAvailableCities(null);
                    landingViewModel.setAvailableZipCodes(null);
                    landingViewModel.setSelectedState(selectedArea);
                    break;
                case "CS":
                    landingViewModel.setAvailableZipCodes(null);
                    landingViewModel.setSelectedCity(selectedArea);
                    break;
                case "ZI":
                    landingViewModel.setSelectedZipCode(selectedArea.getName());
                    break;
                default:
                    landingViewModel.setErrorMessage("Unknown area type: " + type);
                    break;
            }
        }
        else {
            landingViewModel.setErrorMessage("Failed to select area: " + areaOutputData.getArea().getName());
        }
    }

    /**
     * Prepares the view for successful property retrieval.
     * @param propertyOutputData The output data containing property details.
     */
    @Override
    public void preparePropertySuccessView(PropertyOutputData propertyOutputData) {
        System.out.println("presenter: preparing property success view: " + propertyOutputData);
        final Address propertyAddress = propertyOutputData.getPropertyAddress();
        landingViewModel.setPropertyAddress(propertyAddress);
        landingViewModel.getState().setPropertyDetails(propertyOutputData.getPropertyDetails());
        landingViewModel.setPropertyFound(true);

        // Transition to the property confirmation view
        viewManagerModel.setState("propertyConfirmation");
    }

    /**
     * Prepares the view for an error in property retrieval.
     * @param errorMessage The error message to display.
     */
    @Override
    public void preparePropertyFailView(String errorMessage) {
        System.out.println("presenter: preparing property fail view: ");
        landingViewModel.setErrorMessage(errorMessage);
        landingViewModel.setPropertyFound(false);
    }

    /**
     * Prepares an error message view for a general error.
     * @param errorMessage The message to be presented to the user.
     */
    @Override
    public void prepareErrorView(String errorMessage) {
        System.out.println("presenter: preparing error view: ");
        landingViewModel.setErrorMessage(errorMessage);
    }

    /**
     * Switches the view to the sign-up screen.
     */
    @Override
    public void goToSignup() {
        System.out.println("presenter: switching to signup: ");
        viewManagerModel.setState("signup");
    }

    /**
     * Switches the view to the login screen.
     */
    @Override
    public void goToLogin() {
        System.out.println("presenter: switching to login: ");
        viewManagerModel.setState("login");
    }
}
