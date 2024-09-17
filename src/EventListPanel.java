import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class EventListPanel extends JPanel {
    public ArrayList<Event> events;
    private JPanel displayPanel;
    private JPanel controlPanel;
    private JComboBox<String> sortDropDown;
    private JButton addEventButton;
    private JCheckBox completedFilter;
    private JCheckBox meetingFilter;
    private JCheckBox deadlineFilter;

    private static final String NAME_OPTION = "Sort by Name";
    private static final String DATE_OPTION = "Sort by Date";

    public EventListPanel() {
        setLayout(new BorderLayout());

        events = new ArrayList<>();
        controlPanel = new JPanel();
        displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
        sortDropDown = new JComboBox<>(new String[]{NAME_OPTION, DATE_OPTION});
        sortDropDown.addActionListener(e -> updateEventDisplay());

        completedFilter = new JCheckBox("Filter Completed Events");
        completedFilter.addActionListener(e -> updateEventDisplay());

        meetingFilter = new JCheckBox("Filter Meetings");
        meetingFilter.addActionListener(e -> updateEventDisplay());

        deadlineFilter = new JCheckBox("Filter Deadlines");
        deadlineFilter.addActionListener(e -> updateEventDisplay());

        addEventButton = new JButton("Add Event");
        addEventButton.addActionListener(e -> showAddEventModal());

        controlPanel.add(sortDropDown);
        controlPanel.add(completedFilter);
        controlPanel.add(meetingFilter);
        controlPanel.add(deadlineFilter);
        controlPanel.add(addEventButton);

        add(controlPanel, BorderLayout.NORTH);
        add(displayPanel, BorderLayout.CENTER);
    }

    public void addEvent(Event event) {
        events.add(event);
        updateEventDisplay();
    }

    private void updateEventDisplay() {
        displayPanel.removeAll();

        // Create a list to hold filtered events
        ArrayList<Event> filteredEvents = new ArrayList<>();

        // Apply filters
        for (Event event : events) {
            boolean shouldAdd = true;

            // Check completed filter
            if (completedFilter.isSelected() && event instanceof Completable && ((Completable) event).isComplete()) {
                shouldAdd = false;
            }

            // Check meeting filter
            if (meetingFilter.isSelected() && !(event instanceof Meeting)) {
                shouldAdd = false;
            }

            // Check deadline filter
            if (deadlineFilter.isSelected() && !(event instanceof Deadline)) {
                shouldAdd = false;
            }

            if (shouldAdd) {
                filteredEvents.add(event);
            }
        }

        // Sort filtered events
        String selection = (String) sortDropDown.getSelectedItem();
        Comparator<Event> comparator;
        if (NAME_OPTION.equals(selection)) {
            comparator = Comparator.comparing(Event::getName);
        } else if (DATE_OPTION.equals(selection)) {
            comparator = Comparator.comparing(Event::getDateTime);
        } else {
            throw new IllegalArgumentException("Invalid sort option: " + selection);
        }

        filteredEvents.sort(comparator);

        // Add sorted filtered events to display panel
        for (Event event : filteredEvents) {
            EventPanel eventPanel = new EventPanel(event);
            displayPanel.add(eventPanel);
        }

        revalidate();
        repaint();
    }

    private Comparator<Event> getComparator() {
        String selection = (String) sortDropDown.getSelectedItem();
        if (NAME_OPTION.equals(selection)) {
            return Comparator.comparing(Event::getName);
        } else if (DATE_OPTION.equals(selection)) {
            return Comparator.comparing(Event::getDateTime);
        } else {
            throw new IllegalArgumentException("Invalid sort option: " + selection);
        }
    }

    private void showAddEventModal() {
        AddEventModal addEventModal = new AddEventModal(this);
        addEventModal.setVisible(true);
    }
}