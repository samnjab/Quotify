package quotify_app.app;

import javax.swing.JFrame;

/**
 * The Main class of our application.
 */
public class Main {
    /**
     * Builds and runs the CA architecture of the application.
     * @param args unused arguments
     */
    public static void main(String[] args) {

        // Set up the application using AppBuilder
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                .addSignupView()
                .addLoginView()
                .addFunctionView()
                .addComparatorView()
                .addSignupUseCase()
                .addLoginUseCase()
                .addFunctionUseCase()
                .addComparatorUseCase()
                .addCurrentPriceView()
                .addSignupUseCase()
                .addLoginUseCase()
                .addCurrentPriceUseCase()
                .build();

        application.pack();
        application.setVisible(true);
    }
}
