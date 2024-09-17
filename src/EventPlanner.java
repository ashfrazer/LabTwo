import javax.swing.*;
import java.awt.*;
import java.time.Duration;
import java.util.Date;

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
    }

    public static void addDefaultEvents(EventListPanel eventListPanel) {
        // Create new deadline
        String deadlineName = "Lab Two";
        Deadline deadline = new Deadline(deadlineName, new Date());

        // Create new meeting
        String meetingName = "CSCI-3381 Lecture";
        String location = "MCS 339";
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() - Duration.ofHours(1).toMillis());

        Meeting meeting = new Meeting(meetingName, startDate, endDate, location);

        // Add deadline and meeting to the Event List Panel
        eventListPanel.events.add(deadline);
        eventListPanel.events.add(meeting);
    }
}
