package quotify_app.entities;

/**
 * An implementation of the User interface for regular registered User.
 * These are users of the application that have signed up.
 * Guest users should be instantiated with the GuestUser Class (NOT YET IMPLEMENTED).
 */
public class CommonUser implements User {

    private final String name;
    private final String password;
    private final String email;

    public CommonUser(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getEmail() {
        return email;
    }
}
