import javax.swing.*;
import java.awt.*;
import java.time.Duration;
import java.time.LocalDateTime;

public class EventPlanner {
    public static void main(String[] args) {
        // Create Event Planner frame
        JFrame frame = new JFrame("Event Planner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));

        // Create Event List Panel
        EventListPanel eventListPanel = new EventListPanel();
        frame.add(eventListPanel);
        frame.pack();

        addDefaultEvents(eventListPanel);

        frame.setVisible(true);
    }

    public static void addDefaultEvents(EventListPanel eventListPanel) {
        // Create new deadline
        String deadlineName = "Lab Two";
        LocalDateTime deadlineDate = LocalDateTime.of(2024, 9, 25, 15, 0);
        Deadline deadline = new Deadline(deadlineName, deadlineDate);

        // Create new meeting
        String meetingName = "Exam 2";
        String location = "MCS 339";
        LocalDateTime startDate = LocalDateTime.of(2024, 10, 4, 15, 0);
        LocalDateTime endDate = startDate.plus(Duration.ofMinutes(50));

        Meeting meeting = new Meeting(meetingName, startDate, endDate, location);
        // Add deadline and meeting to the Event List Panel
        eventListPanel.events.add(deadline);
        eventListPanel.events.add(meeting);

        // Update display
        eventListPanel.updateEventDisplay();
    }
}
