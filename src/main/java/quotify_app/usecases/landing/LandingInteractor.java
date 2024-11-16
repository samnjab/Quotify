package quotify_app.usecases.landing;

import quotify_app.entities.Region;

/**
 * The Landing Interactor.
 */
public class LandingInteractor implements LandingInputBoundary {
    private final RegionDataAccessInterface regionDataAccessObject;
    private final LandingOutputBoundary landingPresenter;

    public LandingInteractor(RegionDataAccessInterface regionDataAccessInterface,
                           LandingOutputBoundary landingOutputBoundary) {
        this.regionDataAccessObject = regionDataAccessInterface;
        this.landingPresenter = landingOutputBoundary;
    }

    @Override
    public void selectRegion(RegionInputData regionInputData) throws Exception {
        final String regionName = regionInputData.getRegionName();
        final String regionId = regionInputData.getRegionId();

        if (!regionDataAccessObject.existsById(regionId)) {
            throw new Exception("Region Id does not exist in the database");;
        }
        else {
            final Region region = regionDataAccessObject.get(regionId);
            if (!regionName.equals(region.getRegionName())) {
                throw new Exception("Region Id and name do not match");
            }
            else {
                final RegionOutputData regionOutputData = new regionOutputData(region);
                landingPresenter.prepareSuccessView(regionOutputData);
            }
        }
    }

    /**
     * Trigger view transition to Signup through the presenter.
     */
    public void goToSignup() {
        landingPresenter.goToSignup();
    }

    /**
     * Trigger view transition to Login through the presenter.
     */
    public void goToLogin() {
        landingPresenter.goToLogin();
    }
}
