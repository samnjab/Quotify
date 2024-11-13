package quotify_app.entities;

/**
 * Factory for creating CommonUser objects.
 */
public class CommonUserFactory implements UserFactory {

    @Override
    public User create(String name, String password, String email) {
        return new CommonUser(name, password, email);
    }
}
