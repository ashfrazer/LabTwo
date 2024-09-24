import java.time.Duration;
import java.time.LocalDateTime;

public class Meeting extends Event implements Completable {
    private LocalDateTime endDateTime;
    private String location;
    private boolean complete;

    // Constructor
    public Meeting(String name, LocalDateTime dateTime, LocalDateTime endDateTime, String location) {
        super(name, dateTime);
        this.endDateTime = endDateTime;
        this.location = location;
        this.complete = false;
    }

    @Override
    public void complete() {
        complete = !complete; // Allows users to toggle completion status in menu
    }

    @Override
    public boolean isComplete() {
        return complete;
    }

    // Retrieves end time of meeting
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    // Sets end time of meeting
    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    // Retrieves location of meeting
    public String getLocation() {
        return location;
    }

    // Sets location of meeting
    public void setLocation(String location) {
        this.location = location;
    }

    // Retrieves duration of meeting
    public int getDurationHours() {
        Duration duration = Duration.between(getDateTime(), endDateTime);
        return (int) duration.toHours();
    }

    public int getDurationMinutes() {
        Duration duration = Duration.between(getDateTime(), endDateTime);
        return (int) duration.toMinutes();
    }

}
