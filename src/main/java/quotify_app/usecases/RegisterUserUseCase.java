package quotify_app.usecases;

import quotify_app.adapters.UserRepository;
import quotify_app.entities.User;

public class RegisterUserUseCase {
    private final UserRepository userRepository;

    public RegisterUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Updated to return a String with the error message or null on success
    public String registerUser(User user) {
        // Check if the email is already registered
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return "The email is already associated with an account.";
        }

        // Check if the username is already registered
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return "The username is already taken.";
        }

        // If both email and username are unique, save the user
        userRepository.save(user);
        return null;  // Indicate success
    }
}
