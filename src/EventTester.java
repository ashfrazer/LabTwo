import java.util.Calendar;
import java.util.Date;

public class EventTester {
    public static void main(String[] args) {
        // Test Event class
        System.out.println("Testing Event class:");
        Event event = new Deadline("Lab Two Deadline", new Date());
        System.out.println("Event name: " + event.getName());
        System.out.println("Event date and time: " + event.getDateTime());

        // Test Deadline class
        System.out.println("\nTesting Deadline class:");
        Deadline deadline = new Deadline("Lab Two Deadline", new Date());
        System.out.println("Deadline name: " + deadline.getName());
        System.out.println("Deadline date and time: " + deadline.getDateTime());

        // Test Meeting class
        System.out.println("\nTesting Meeting class:");
        Calendar startCal = Calendar.getInstance();
        startCal.set(2024, Calendar.OCTOBER, 4, 15, 0);
        Date startDate = startCal.getTime();

        Calendar endCal = Calendar.getInstance();
        endCal.set(2024, Calendar.OCTOBER, 4, 15, 50);
        Date endDate = endCal.getTime();

        Meeting meeting = new Meeting("Module 2 Exam", startDate, endDate, "MCS 339");

        // Print meeting info
        System.out.println("Meeting Name: " + meeting.getName());
        System.out.println("Meeting Start Date and Time: " + meeting.getDateTime());
        System.out.println("Meeting End Date and Time: " + meeting.getEndDateTime());
        System.out.println("Meeting Location: " + meeting.getLocation());

        // Set meeting as complete & print status
        meeting.complete();
        System.out.println("Meeting Complete: " + meeting.isComplete());

        // Calculate and print duration of meeting
        int duration = meeting.getDuration();
        System.out.println("Meeting Duration (minutes): " + duration);
    }
}
