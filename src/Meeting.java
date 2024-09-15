import java.util.Date;

public class Meeting extends Event implements Completable {
    private Date endDateTime;
    private String location;
    private boolean complete;

    // Constructor
    public Meeting(String name, Date dateTime, Date endDateTime, String location) {
        super(name, dateTime);
        this.endDateTime = endDateTime;
        this.location = location;
        this.complete = false;
    }

    @Override
    public void complete() {
        this.complete = true;
    }

    @Override
    public boolean isComplete() {
        return complete;
    }

    // Retrieves end time of meeting
    public Date getEndDateTime() {
        return endDateTime;
    }

    // Sets end time of meeting
    public void setEndDateTime(Date endDateTime) {
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
    public int getDuration() {
        float durationMS = endDateTime.getTime() - getDateTime().getTime();
        return (int) (durationMS / (1000 * 60)); // Converts milliseconds to seconds
    }

}
