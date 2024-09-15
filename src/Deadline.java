import java.util.Date;

public class Deadline extends Event implements Completable {
    private boolean complete;

    // Constructor
    public Deadline(String name, Date dateTime) {
        super(name, dateTime);
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
}
