package quotify_app.usecases.function;

import quotify_app.data_access.DBUserDataAccessObject;
import quotify_app.entities.CommonUser;
import quotify_app.entities.User;
import quotify_app.entities.UserFactory;
import quotify_app.usecases.signup.SignupOutputBoundary;
import quotify_app.usecases.signup.SignupOutputData;
import quotify_app.usecases.signup.SignupUserDataAccessInterface;
import quotify_app.data_access.DBUserDataAccessObject;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * Interactor for handling signup logic.
 */
public class FunctionInteractor implements FunctionInputBoundary {
    private final FunctionUserDataAccessInterface userDataAccessObject;
    private final FunctionOutputBoundary functionPresenter;

    public FunctionInteractor(FunctionUserDataAccessInterface userDataAccessObject,
                              FunctionOutputBoundary functionPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.functionPresenter = functionPresenter;
    }

    @Override
    public void execute() {

    }

    /**
     * Trigger view transition to currentPrice through the presenter.
     */
    public void goToCurrentPriceGuest() {
        functionPresenter.goToCurrentPriceGuest();
    }

    /**
     * Trigger view transition to currentPriceLoggedIn.
     */
    public void goToCurrentPriceUser() {
        functionPresenter.goToCurrentPriceUser();
    }

    /**
     * Trigger view transition to comparator.
     */
    public void goToComparatorGuest() {
        functionPresenter.goToComparatorGuest();
    }

    /**
     * Trigger view transition to comparatorLoggedIn.
     */
    public void goToComparatorUser() {
        functionPresenter.goToComparatorUser();
    }
}
