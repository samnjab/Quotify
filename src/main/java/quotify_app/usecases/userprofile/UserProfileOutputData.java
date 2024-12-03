package quotify_app.usecases.userprofile;

/**
 * A class representing the data which will update the state of the User Profile View.
 */
public class UserProfileOutputData {
    private String username;
    private String email;
    private String createdAt;

    public UserProfileOutputData(String username, String email, String createdAt) {
        this.username = username;
        this.email = email;
        this.createdAt = createdAt;
    }

    /**
     * Getter for username.
     * @return String username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter for email.
     * @return String email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Getter for timestamp.
     * @return String timestamp.
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Setter for username.
     * @param username the input username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Setter for email.
     * @param email the input email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Setter for timestamp.
     * @param createdAt the input timestamp.
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
