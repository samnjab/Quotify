package quotify_app.ui;

import quotify_app.adapters.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserProfilePanel extends JPanel {
    private final UserController userController;
    private final MainFrame mainFrame;

    public UserProfilePanel(MainFrame mainFrame, UserController userController) {
        this.mainFrame = mainFrame;
        this.userController = userController;

        setLayout(new BorderLayout());

        JLabel profileLabel = new JLabel("User Profile", SwingConstants.CENTER);
        profileLabel.setFont(new Font("Arial", Font.BOLD, 22));
        add(profileLabel, BorderLayout.NORTH);

        // Delete button at the bottom of the screen
        JButton deleteUserButton = new JButton("Delete User");
        deleteUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(UserProfilePanel.this, "Are you sure you want to delete your account?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    userController.deleteUser();
                    JOptionPane.showMessageDialog(UserProfilePanel.this, "Your account has been deleted.");
                    mainFrame.showPanel("Login");
                }
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(deleteUserButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
