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

    // Constructor
    public AddEventModal(EventListPanel eventListPanel) {
        this.eventListPanel = eventListPanel;
        // Add Event Modal
        setTitle("Add Event");
        setModal(true);
        setLayout(new GridLayout(0, 2)); // 2 columns: Label & TextField

        // Prompt user for event type
        add(new JLabel("Event Type:"));
        eventTypeComboBox = new JComboBox<>(new String[]{"Meeting", "Deadline"});
        add(eventTypeComboBox);

        // Event name field
        add(new JLabel("Event Name:"));
        nameField = new JTextField();
        add(nameField);

        // Event start time field
        add(new JLabel("Start Time (YYYY-MM-DD HH:mm):"));
        startTimeField = new JTextField();
        add(startTimeField);

        // Event end time field
        add(new JLabel("End Time (YYYY-MM-DD HH:mm):"));
        endTimeField = new JTextField();
        add(endTimeField);

        // Event location field
        add(new JLabel("Location:"));
        locationField = new JTextField();
        add(locationField);

        // Add Event button
        JButton addButton = new JButton("Add Event");
        addButton.addActionListener(new AddEventAction());
        JButton cancelButton;

        // Create Cancel button if user no longer wants to add event
        add(cancelButton = new JButton("Cancel"));
        cancelButton.addActionListener(e -> dispose());

        // Add buttons
        add(addButton);
        add(cancelButton);

        // Fit content onto panel
        pack();
        setLocationRelativeTo(eventListPanel);
    }

    // Add Event Action logic
    private class AddEventAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Retrieve name and event type
            String name = nameField.getText();
            String eventType = (String) eventTypeComboBox.getSelectedItem();
            try {
                // Parse start and end DateTime from text fields
                LocalDateTime startDateTime = LocalDateTime.parse(startTimeField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                LocalDateTime endDateTime = LocalDateTime.parse(endTimeField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                String location = locationField.getText(); // Retrieve location

                //Create new meeting or deadline based on user input
                if ("Meeting".equals(eventType)) {
                    Meeting newMeeting = new Meeting(name, startDateTime, endDateTime, location);
                    eventListPanel.addEvent(newMeeting);
                } else if ("Deadline".equals(eventType)) {
                    Deadline newDeadline = new Deadline(name, startDateTime); // Assuming only name and dateTime for Deadline
                    eventListPanel.addEvent(newDeadline);
                }

                dispose();
            } catch (DateTimeParseException ex) {
                // Display error message if user uses incorrect DateTime format
                JOptionPane.showMessageDialog(AddEventModal.this, "Invalid date format. Please "+
                                "use 'YYYY-MM-DD HH:mm'.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}