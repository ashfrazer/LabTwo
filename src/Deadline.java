import java.time.LocalDateTime;

public class Deadline extends Event implements Completable {
    private boolean complete;

    // Constructor
    public Deadline(String name, LocalDateTime dateTime) {
        super(name, dateTime);
        this.complete = false;
    }

    // Sets event to complete
    @Override
    public void complete() {
        complete = !complete; // Allows users to toggle completion status in menu
    }

    // Returns completion status
    @Override
    public boolean isComplete() {
        return complete;
    }
}
