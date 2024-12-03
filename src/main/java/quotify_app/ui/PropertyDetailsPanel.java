package quotify_app.ui;

import java.awt.*;
import java.util.Map;

import javax.swing.*;

public class PropertyDetailsPanel extends JPanel {

    /**
     * Constructs a PropertyDetailsPanel.
     * @param address         The property address to display.
     * @param propertyDetails Map containing property details as key-value pairs.
     */
    public PropertyDetailsPanel(String address, Map<String, String> propertyDetails) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add Address Section
        JLabel addressLabel = new JLabel("Address: " + address);
        addressLabel.setFont(new Font("Arial", Font.BOLD, 16));
        addressLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(addressLabel, BorderLayout.NORTH);

        // Add Property Details Section
        JPanel detailsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        int row = 0;
        for (Map.Entry<String, String> entry : propertyDetails.entrySet()) {
            // Create and add the key label
            JLabel keyLabel = new JLabel(entry.getKey() + ":");
            gbc.gridx = 0;
            gbc.gridy = row;
            gbc.weightx = 0.3;
            detailsPanel.add(keyLabel, gbc);

            // Create and add the value display (e.g., a non-editable text box)
            JTextField valueField = new JTextField(entry.getValue());
            valueField.setEditable(false);
            gbc.gridx = 1;
            gbc.weightx = 0.7;
            detailsPanel.add(valueField, gbc);

            row++;
        }
        add(detailsPanel, BorderLayout.CENTER);
    }
}
