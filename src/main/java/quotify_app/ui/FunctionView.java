package quotify_app.ui;

import quotify_app.adapters.function.FunctionController;
import quotify_app.adapters.function.FunctionState;
import quotify_app.adapters.login.LoginController;
import quotify_app.adapters.function.FunctionViewModel;
import quotify_app.adapters.login.LoginState;
import quotify_app.adapters.login.LoginViewModel;
import quotify_app.adapters.signup.SignupState;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The view for logging in.
 */
public class FunctionView extends JPanel {
    private final String viewName = "function";
    private FunctionController functionController;
    private final FunctionViewModel functionViewModel;

    public FunctionView(FunctionViewModel functionViewModel) {
        this.functionViewModel = functionViewModel;
        this.functionViewModel.addPropertyChangeListener(this);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        final JLabel titleLabel = new JLabel(FunctionViewModel.TITLE_LABEL);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(titleLabel);

        // triggers the current price usecase upon button click
        final JButton goToCurrentPriceButton = new JButton(FunctionViewModel.ESTIMATE_PRICE_LABEL);
        goToCurrentPriceButton.addActionListener(evt -> functionController.goToCurrentPriceGuest());
        add(goToCurrentPriceButton);

        // triggers the comparator usecase upon button click
        final JButton goToComaparatorButton = new JButton(FunctionViewModel.COMPARE_LABEL);
        goToComaparatorButton.addActionListener(evt -> functionController.goToComparatorGuest());
        add(goToComaparatorButton);

    }

    public String getViewName() {
        return viewName;
    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == goToCurrentPriceButton) {
//            functionController.goToCurrentPriceGuest();
//        } else if (e.getSource() == goToComparatorButton) {
//            functionController.goToComparatorGuest();
//        }
//    }
}
