package quotify_app.adapters.landing;

import java.util.List;

import quotify_app.adapters.ViewManagerModel;
import quotify_app.entities.regionEntities.Area;
import quotify_app.usecases.landing.AreaListOutputData;
import quotify_app.usecases.landing.AreaOutputData;
import quotify_app.usecases.landing.LandingOutputBoundary;

/**
 * The Presenter for the Select Address Use Case.
 */
public class LandingPresenter implements LandingOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final LandingViewModel landingViewModel;

    public LandingPresenter(
            ViewManagerModel viewManagerModel,
            LandingViewModel landingViewModel
    ) {
        this.viewManagerModel = viewManagerModel;
        this.landingViewModel = landingViewModel;
    }

    @Override
    public void prepareAreaSuccessView(AreaOutputData areaOutputData) {
        if (!areaOutputData.isSelectionFailed()) {
            switch (areaOutputData.getArea().getType()) {
                case "country":
                    landingViewModel.setAvailableStates(null);
                    landingViewModel.setAvailableCities(null);
                    landingViewModel.getState().setSelectedCountry(areaOutputData.getArea());
                    break;
                case "state":
                    landingViewModel.setAvailableCities(null);
                    landingViewModel.getState().setSelectedState(areaOutputData.getArea());
                    break;
                case "city":
                    landingViewModel.getState().setSelectedCity(areaOutputData.getArea());
                    break;
                default:
                    break;
            }
        }
        else {
            landingViewModel.getState().setErrorMessage("Failed to select region: "
                    + areaOutputData.getArea().getName());
        }
    }

    @Override
    public void prepareErrorView(String errorMessage) {
        landingViewModel.getState().setErrorMessage(errorMessage);
    }

    @Override
    public void prepareAreaListSuccessView(AreaListOutputData areaListOutputData) {
        if (!areaListOutputData.isSelectionFailed()) {
            final List<Area> areas = areaListOutputData.getAreas();
            final String areaType = areaListOutputData.getAreaType();

            switch (areaType) {
                case "country":
                    landingViewModel.setAvailableCountries(areas);
                    break;
                case "state":
                    landingViewModel.setAvailableStates(areas);
                    break;
                case "city":
                    landingViewModel.setAvailableCities(areas);
                    break;
                default:
                    landingViewModel.getState().setErrorMessage("Unknown area type for list: " + areaType);
                    break;
            }
        }
        else {
            landingViewModel.getState().setErrorMessage("Failed to fetch area list.");
        }
    }

    /**
     * Prepares the failure view for the fetch list of area Use Case.
     * @param errorMessage the explanation of the failure
     */
    @Override
    public void prepareAreaListFailView(String errorMessage) {
        landingViewModel.getState().setErrorMessage("Failed to fetch areas: " + errorMessage);
    }

    @Override
    public void goToSignup() {
        // Reset the state when navigating
        landingViewModel.getState().resetState();
        viewManagerModel.setState("signup");
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void goToLogin() {
        // Reset the state when navigating
        landingViewModel.getState().resetState();
        viewManagerModel.setState("login");
        viewManagerModel.firePropertyChanged();
    }

}
