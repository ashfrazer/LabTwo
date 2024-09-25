import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddEventModal extends JDialog {
    private final EventListPanel eventListPanel;
    private final JTextField nameField;
    private final JTextField startTimeField;
    private final JTextField endTimeField;
    private final JTextField locationField;
    private final JComboBox<String> eventTypeComboBox;
    private final JLabel startTimeLabel;
    private final JLabel endTimeLabel;
    private final JLabel locationLabel;

    // Constructor
    public AddEventModal(EventListPanel eventListPanel) {
        this.eventListPanel = eventListPanel;
        // Add Event Modal
        setTitle("Add Event");
        setModal(true);
        setLayout(new GridLayout(0, 2)); // 2 columns: Label & TextField

        // Prompt user for event type (Drop down box)
        add(new JLabel("Event Type:"));
        eventTypeComboBox = new JComboBox<>(new String[]{"Meeting", "Deadline"});
        // Adjust visible labels based on selected event (different fields)
        eventTypeComboBox.addActionListener(e -> adjustLabelVisibility()); // Add action listener to handle event type change
        add(eventTypeComboBox);

        // Event name field
        add(new JLabel("Event Name:"));
        nameField = new JTextField();
        add(nameField);

        // Event start time field
        startTimeLabel = new JLabel("Start Time: (YYYY-MM-DD HH:mm):");
        add(startTimeLabel);
        startTimeField = new JTextField();
        add(startTimeField);

        // Event end time field
        endTimeLabel = new JLabel("End Time: (YYYY-MM-DD HH:mm):");
        add(endTimeLabel);
        endTimeField = new JTextField();
        add(endTimeField);

        // Event location field
        locationLabel = new JLabel("Location:");
        add(locationLabel);
        locationField = new JTextField();
        add(locationField);

        // Add Event button
        JButton addButton = new JButton("Add Event");
        addButton.addActionListener(new AddEventAction());

        // Cancel button for when user no longer wants to add event
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());

        // Add buttons
        add(addButton);
        add(cancelButton);

        // Adjusts Add Event modal fields based on event type
        adjustLabelVisibility();

        // Fit content onto panel
        pack();
        setLocationRelativeTo(eventListPanel);
    }

    // Adjusts the labels displayed on Add Event modal. Meetings and Deadlines have different fields for input.
    private void adjustLabelVisibility() {
        String eventType = (String) eventTypeComboBox.getSelectedItem();
        boolean isDeadline = eventType.equals("Deadline");

        // Adjust visibility based on event type
        if (isDeadline) { // If event = Deadline
            startTimeLabel.setText("Due: (YYYY-MM-DD HH:mm)"); // Change label for Deadline
            // Hide End Time and Location labels/fields for Deadline events
            endTimeLabel.setVisible(false);
            endTimeField.setVisible(false);
            locationLabel.setVisible(false);
            locationField.setVisible(false);
        } else { // If event = Meeting
            // Reset/display End Time and Location labels/fields for Meeting events
            startTimeLabel.setText("Start Time: (YYYY-MM-DD HH:mm)"); // Reset label for Meetings
            endTimeLabel.setVisible(true);
            endTimeField.setVisible(true);
            locationLabel.setVisible(true);
            locationField.setVisible(true);
        }
        revalidate();
        repaint();
    }

    // Add Event Action logic
    private class AddEventAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Retrieve name and event type
            String name = nameField.getText();
            String eventType = (String) eventTypeComboBox.getSelectedItem();
            LocalDateTime endDateTime = null; // Initialize to null for Deadlines
            String location = null; // Initialize to null for Deadlines

            try {
                // Parse start DateTime from text field
                LocalDateTime startDateTime = LocalDateTime.parse(startTimeField.getText(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

                // Parse fields based on event type
                if ("Meeting".equals(eventType)) {
                    endDateTime = LocalDateTime.parse(endTimeField.getText(),
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    location = locationField.getText();

                    // Create a new Meeting
                    Meeting newMeeting = new Meeting(name, startDateTime, endDateTime, location);
                    eventListPanel.addEvent(newMeeting);
                } else if ("Deadline".equals(eventType)) {
                    // Create a new Deadline
                    Deadline newDeadline = new Deadline(name, startDateTime);
                    eventListPanel.addEvent(newDeadline);
                }

                dispose();
            } catch (DateTimeParseException ex) {
                // Display error message if user uses incorrect DateTime format
                JOptionPane.showMessageDialog(AddEventModal.this, "Invalid date format. " +
                        "Please use 'YYYY-MM-DD HH:mm'.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    }
}
