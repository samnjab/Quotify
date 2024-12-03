package quotify_app.adapters.landing;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import quotify_app.adapters.ViewManagerModel;
import quotify_app.adapters.function.FunctionViewModel;
import quotify_app.adapters.login.LoginViewModel;
import quotify_app.adapters.signup.SignupViewModel;
import quotify_app.adapters.userprofile.UserProfileViewModel;
import quotify_app.app.ApplicationState;
import quotify_app.usecases.landing.AreaDataTransferObj;
import quotify_app.usecases.landing.AreaListDataTransferObj;
import quotify_app.usecases.landing.LandingOutputBoundary;
import quotify_app.usecases.landing.PropertyOutputData;

/**
 * The presenter for the Landing Page use case. Prepares the data for display in the view
 * and updates the LandingViewModel accordingly.
 */
public class LandingPresenter implements LandingOutputBoundary {

    private final LandingViewModel landingViewModel;
    private final SignupViewModel signupViewModel;
    private final LoginViewModel loginViewModel;
    private final FunctionViewModel functionViewModel;
    private final ViewManagerModel viewManagerModel;
    private final UserProfileViewModel userProfileViewModel;

    /**
     * Constructs a new LandingPresenter with the specified view models.
     * @param landingViewModel  The view model for managing Landing Page state.
     * @param viewManagerModel  The view model for managing view transitions.
     * @param signupViewModel the view model for sign up.
     * @param loginViewModel the view model for login.
     * @param functionViewModel the view model for function page.
     * @param userProfileViewModel the view omdel for userprofile.
     */
    public LandingPresenter(LandingViewModel landingViewModel,
                            ViewManagerModel viewManagerModel,
                            SignupViewModel signupViewModel,
                            LoginViewModel loginViewModel,
                            FunctionViewModel functionViewModel,
                            UserProfileViewModel userProfileViewModel) {
        this.landingViewModel = landingViewModel;
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
        this.functionViewModel = functionViewModel;
        this.userProfileViewModel = userProfileViewModel;

        ApplicationState.getInstance().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("isLoggedIn".equals(evt.getPropertyName())) {
                    final boolean isLoggedIn = (Boolean) evt.getNewValue();
                    landingViewModel.setLoggedIn(isLoggedIn);
                }
            }
        });
    }

    /**
     * Prepares the view for successful area data retrieval.
     * @param areaListDataTransferObj The output data containing the list of areas and their type.
     */
    @Override
    public void prepareAreaListSuccessView(AreaListDataTransferObj areaListDataTransferObj) {
        if (!areaListDataTransferObj.isSelectionFailed()) {
            final List<AreaDataTransferObj> areas = areaListDataTransferObj.getAreaDtoList();
            final String type = areaListDataTransferObj.getAreaType();

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
        landingViewModel.setErrorMessage(errorMessage);
    }

    /**
     * Prepares the view for a successful area selection.
     * @param areaDataTransferObj The output data containing the selected area result.
     */
    @Override
    public void prepareAreaSuccessView(AreaDataTransferObj areaDataTransferObj) {
        if (!areaDataTransferObj.isSelectionFailed()) {
            final String type = areaDataTransferObj.getType();

            switch (type) {
                case "CN":
                    landingViewModel.setSelectedCountry(areaDataTransferObj);
                    break;
                case "ST":
                    landingViewModel.setSelectedState(areaDataTransferObj);
                    break;
                case "CS":
                    landingViewModel.setSelectedCity(areaDataTransferObj);
                    break;
                case "ZI":
                    landingViewModel.setSelectedZipCode(areaDataTransferObj);
                    break;
                default:
                    landingViewModel.setErrorMessage("Unknown area type: " + type);
                    break;
            }
        }
        else {
            landingViewModel.setErrorMessage("Failed to select area: " + areaDataTransferObj.getAreaName());
        }
    }

    /**
     * Prepares the view for successful property retrieval.
     * @param propertyOutputData The output data containing property details.
     */
    @Override
    public void preparePropertySuccessView(PropertyOutputData propertyOutputData) {
        landingViewModel.getState().setPropertyAddress(propertyOutputData.getPropertyAddress());
        landingViewModel.getState().setPropertyDetails(propertyOutputData.getPropertyDetails());
        landingViewModel.setPropertyFound(true);
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares the view for an error in property retrieval.
     * @param errorMessage The error message to display.
     */
    @Override
    public void preparePropertyFailView(String errorMessage) {
        landingViewModel.setErrorMessage(errorMessage);
        landingViewModel.setPropertyFound(false);
    }

    /**
     * Prepares an error message view for a general error.
     * @param errorMessage The message to be presented to the user.
     */
    @Override
    public void prepareErrorView(String errorMessage) {
        landingViewModel.setErrorMessage(errorMessage);
    }

    @Override
    public void prepareNextPageNavigation() {
        // Transition to function view
        landingViewModel.getState().setPropertyConfirmed(true);
        viewManagerModel.setState(functionViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Switches the view to the sign-up screen.
     */
    @Override
    public void goToSignup() {
        // Transition to signup view
        viewManagerModel.setState(signupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Switches the view to the login screen.
     */
    @Override
    public void goToLogin() {
        // Transition to login view
        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void presentGoToUserProfile() {
        viewManagerModel.setState("user profile");
        viewManagerModel.firePropertyChanged();
        userProfileViewModel.firePropertyChanged();
    }

    @Override
    public void updateLoginStatus() {
        final boolean isLoggedIn = ApplicationState.getInstance().isLoggedIn();
        landingViewModel.setLoggedIn(isLoggedIn);
    }

}
