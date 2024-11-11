package quotify_app.ui;

import quotify_app.adapters.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoggedInPanel extends JPanel {
    private final UserController userController;
    private final JLabel welcomeLabel;
    private final MainFrame mainFrame;

    public LoggedInPanel(MainFrame mainFrame, UserController userController) {
        this.mainFrame = mainFrame;
        this.userController = userController;

        setLayout(new BorderLayout());

        // Welcome message at the top-right corner
        welcomeLabel = new JLabel("Welcome, " + userController.getCurrentUser() + "!", SwingConstants.RIGHT);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(welcomeLabel, BorderLayout.EAST);

        // Profile button under welcome message
        JButton profileButton = new JButton("Profile");
        profileButton.setPreferredSize(new Dimension(80, 25));
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showPanel("UserProfile");  // Navigate to User Profile screen
            }
        });

        JPanel profileButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        profileButtonPanel.add(profileButton);
        topPanel.add(profileButtonPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);

        // Main content
        JLabel instructionsLabel = new JLabel("To get started, choose a location from the drop-down menu below.");
        instructionsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(instructionsLabel, BorderLayout.CENTER);

        // Add logout button at the bottom
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userController.logout();
                mainFrame.showPanel("Login");
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(logoutButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void updateWelcomeMessage() {
        welcomeLabel.setText("Welcome, " + userController.getCurrentUser() + "!");
    }
}
