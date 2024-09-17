import javax.swing.*;
import java.awt.*;

public class EventPanel extends JPanel {

    public EventPanel(Event event) {
        setLayout(new BorderLayout());

        JLabel nameLabel = new JLabel("Name: " + event.getName());
        JLabel timeLabel = new JLabel("Time: " + event.getDateTime().toString());

        // Add Complete button
        if (event instanceof Completable) {
            JButton completeButton = new JButton("Complete");
            completeButton.addActionListener(e -> ((Completable) event).complete());
            add(completeButton, BorderLayout.EAST);
        }
    }
}
