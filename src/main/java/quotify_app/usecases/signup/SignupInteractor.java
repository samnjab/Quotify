package quotify_app.usecases.signup;

import quotify_app.entities.User;
import quotify_app.entities.UserFactory;

/**
 * Interactor for handling signup logic.
 */
public class SignupInteractor implements SignupInputBoundary {
    private final SignupUserDataAccessInterface userDataAccessObject;
    private final SignupOutputBoundary signupPresenter;
    private final UserFactory userFactory;

    public SignupInteractor(SignupUserDataAccessInterface userDataAccessObject,
                            SignupOutputBoundary signupPresenter,
                            UserFactory userFactory) {
        this.userDataAccessObject = userDataAccessObject;
        this.signupPresenter = signupPresenter;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(SignupInputData inputData) {
        if (inputData.getEmail() == null || inputData.getEmail().trim().isEmpty()) {
            signupPresenter.prepareFailView("Email cannot be empty.");
            System.out.println("Empty email provided");
        }
        else if (userDataAccessObject.existsByName(inputData.getUsername())) {
            signupPresenter.prepareFailView("Username already exists.");
        }
        else if (userDataAccessObject.existsByEmail(inputData.getEmail())) {
            signupPresenter.prepareFailView("Email already in use.");
        }
        else {
            final User newUser = userFactory.create(
                    inputData.getUsername(),
                    inputData.getPassword(),
                    inputData.getEmail()
            );
            userDataAccessObject.save(newUser);
            signupPresenter.prepareSuccessView(new SignupOutputData(newUser.getName()));
            System.out.println("successful registration");
        }
    }

    @Override
    public void goToLogin() {
        signupPresenter.goToLogin();
    }
}
