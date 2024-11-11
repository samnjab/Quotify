package quotify_app.adapters;

import quotify_app.entities.User;

public interface UserRepository {
    void save(User user);
    User findByUsername(String username);
    User findByEmail(String email);
    void deleteByUsername(String username);  // New method for deleting a user
}
