package quotify_app.ui;

import quotify_app.adapters.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoggedInPanel extends JPanel {
    private final UserController userController;
    private final JLabel welcomeLabel;
    private final JLabel selectedLocationLabel;
    private final MainFrame mainFrame;

    public LoggedInPanel(MainFrame mainFrame, UserController userController) {
        this.mainFrame = mainFrame;
        this.userController = userController;

        setLayout(new BorderLayout());

        // Welcome message and profile button at the top
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
                if (userController.getCurrentUser().equals("guest")) {
                    // Show dialog if user is a guest
                    showGuestAccessDialog();
                } else {
                    // Navigate to User Profile screen if user is not a guest
                    mainFrame.showPanel("UserProfile");
                }
            }
        });

        JPanel profileButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        profileButtonPanel.add(profileButton);
        topPanel.add(profileButtonPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);

        // Center panel with instructions, dropdown, and selected location label
        JPanel centerPanel = new JPanel(new BorderLayout());

        // Instructions label
        JLabel instructionsLabel = new JLabel("To get started, choose a location from the drop-down menu below.");
        instructionsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        centerPanel.add(instructionsLabel, BorderLayout.NORTH);

        // Dropdown menu with cities
        String[] cities = {"city1", "city2", "city3"};
        JComboBox<String> cityDropdown = new JComboBox<>(cities);

        // Set preferred size for dropdown to make it smaller
        cityDropdown.setPreferredSize(new Dimension(100, 25));
        JPanel dropdownPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        dropdownPanel.add(cityDropdown);
        centerPanel.add(dropdownPanel, BorderLayout.CENTER);

        // Label to show selected city
        selectedLocationLabel = new JLabel("Selected location: " + cities[0], SwingConstants.CENTER);
        centerPanel.add(selectedLocationLabel, BorderLayout.SOUTH);

        // Action listener to update selected location label based on dropdown selection
        cityDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCity = (String) cityDropdown.getSelectedItem();
                selectedLocationLabel.setText("Selected location: " + selectedCity);
            }
        });

        add(centerPanel, BorderLayout.CENTER);

        // Logout button at the bottom
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

    // Method to show dialog for guest users trying to access the profile
    private void showGuestAccessDialog() {
        int option = JOptionPane.showOptionDialog(
                this,
                "To access the profile, you must either register or log in.",
                "Guest Access Restricted",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new Object[]{"Register", "Login", "Cancel"},
                "Register"
        );

        // Handle the user's choice
        switch (option) {
            case 0: // Register
                mainFrame.showPanel("Register");
                break;
            case 1: // Login
                mainFrame.showPanel("Login");
                break;
            case 2: // Cancel
                // Do nothing and close the dialog
                break;
        }
    }
}
