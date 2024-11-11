package quotify_app.ui;

import javax.swing.*;
import java.awt.*;

public class HeaderPanel extends JPanel {
    public HeaderPanel() {
        JLabel headerLabel = new JLabel("Welcome to Quotify", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 22));
        setLayout(new BorderLayout());
        add(headerLabel, BorderLayout.CENTER);
    }
}

