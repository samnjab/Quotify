package quotify_app.usecases;

import quotify_app.adapters.UserRepository;

public class DeleteUserUseCase {
    private final UserRepository userRepository;

    public DeleteUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void deleteUser(String username) {
        userRepository.deleteByUsername(username);
    }
}
