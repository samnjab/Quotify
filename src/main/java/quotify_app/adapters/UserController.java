package quotify_app.adapters;

import quotify_app.entities.User;
import quotify_app.usecases.DeleteUserUseCase;
import quotify_app.usecases.RegisterUserUseCase;
import quotify_app.usecases.LoginUserUseCase;

public class UserController {
    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUserUseCase loginUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private String currentUser;

    public UserController(UserRepository userRepository) {
        this.registerUserUseCase = new RegisterUserUseCase(userRepository);
        this.loginUserUseCase = new LoginUserUseCase(userRepository);
        this.deleteUserUseCase = new DeleteUserUseCase(userRepository);
        this.currentUser = "guest";
    }

    public String registerUser(String email, String username, String password) {
        User user = new User(email, username, password);
        return registerUserUseCase.registerUser(user);
    }

    public boolean loginUser(String username, String password) {
        boolean success = loginUserUseCase.loginUser(username, password);
        if (success) {
            this.currentUser = username;
        }
        return success;
    }

    public void deleteUser() {
        deleteUserUseCase.deleteUser(currentUser);
        logout();  // Reset user state after deletion
    }

    public boolean continueAsGuest() {
        this.currentUser = "guest";
        return true;
    }

    public void logout() {
        this.currentUser = "guest";
    }

    public String getCurrentUser() {
        return currentUser;
    }
}
