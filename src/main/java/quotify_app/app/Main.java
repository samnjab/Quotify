package quotify_app.app;

import javax.swing.JFrame;
import quotify_app.data_access.DatabaseConnection;

/**
 * The Main class of our application.
 */
public class Main {
    public static void main(String[] args) {

        // Set up the application using AppBuilder
        AppBuilder appBuilder = new AppBuilder();
        JFrame application = appBuilder
                .addSignupView()
                .addLoginView()
                .addSignupUseCase()
                .addLoginUseCase()
                .build();

        application.pack();
        application.setVisible(true);
    }
}
