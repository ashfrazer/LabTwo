import java.util.Date;

public abstract class Event implements Comparable<Event> {
    private String name;
    private Date dateTime;

    // Constructor
    public Event(String name, Date dateTime) {
        this.name = name;
        this.dateTime = dateTime;
    }

    // Retrieves event name
    public String getName() {
        return name;
    }

    // Sets event name
    public void setName(String name) {
        this.name = name;
    }

    // Retrieves event date and time
    public Date getDateTime() {
        return dateTime;
    }

    // Sets event date and time
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    // Compares two events based on date and time
    @Override
    public int compareTo(Event o) {
        return this.dateTime.compareTo(o.getDateTime());
    }
}
