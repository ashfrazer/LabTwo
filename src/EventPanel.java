import javax.swing.*;
import java.awt.*;

public class EventPanel extends JPanel {
    // Event details labels
    private JLabel nameLabel;
    private JLabel timeLabel;
    private JLabel locationLabel;
    private JLabel durationLabel;
    private JLabel completedLabel;

    // Constructor
    public EventPanel(Event event) {
        setLayout(new BorderLayout());

        // Name and time labels
        nameLabel = new JLabel("Name: " + event.getName());
        timeLabel = new JLabel("Date: " + event.getDateTime().toString());

        // Panel for event details
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(nameLabel);
        mainPanel.add(timeLabel);

        // Check if event is a meeting
        if (event instanceof Meeting meeting) {
            // Add location, duration, and completed labels
            locationLabel = new JLabel("Location: " + meeting.getLocation());
            durationLabel = new JLabel("Duration: " + meeting.getDurationMinutes() + " minutes");
            completedLabel = new JLabel("Completed: " + meeting.isComplete());

            JPanel extraDetailsPanel = new JPanel();
            extraDetailsPanel.setLayout(new BoxLayout(extraDetailsPanel, BoxLayout.Y_AXIS));
            extraDetailsPanel.add(locationLabel);
            extraDetailsPanel.add(durationLabel);
            extraDetailsPanel.add(completedLabel);

            mainPanel.add(extraDetailsPanel);
        }

        if (event instanceof Deadline deadline) {
            // Add completed label
            completedLabel = new JLabel("Completed: " + deadline.isComplete());
            JPanel extraDetailsPanel = new JPanel();
            extraDetailsPanel.setLayout(new BoxLayout(extraDetailsPanel, BoxLayout.Y_AXIS));
            extraDetailsPanel.add(completedLabel);

            mainPanel.add(extraDetailsPanel);
        }

        add(mainPanel, BorderLayout.CENTER);

        if (event instanceof Completable completableEvent) {
            JButton completeButton = new JButton("Click to Complete");
            completeButton.addActionListener(e -> {
                // Toggle completion status
                if (completableEvent.isComplete()) {
                    completableEvent.complete(); // Mark as incomplete
                } else {
                    completableEvent.complete(); // Mark as complete
                }

                // Update completedLabel text
                completedLabel.setText("Completed: " + completableEvent.isComplete());
            });
            add(completeButton, BorderLayout.SOUTH);
        }

        revalidate();
        repaint();
    }
}
