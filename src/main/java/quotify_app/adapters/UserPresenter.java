package quotify_app.adapters;

public class UserPresenter {
    public String getSuccessMessage() {
        return "Operation successful!";
    }

    public String getErrorMessage() {
        return "Operation failed. Please try again.";
    }

    public String getLoginErrorMessage() {
        return "Invalid username or password.";
    }
}
