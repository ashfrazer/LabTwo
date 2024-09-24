import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class EventListPanel extends JPanel {
    public ArrayList<Event> events;
    private final JPanel displayPanel;
    private final JComboBox<String> sortDropDown;
    private final JCheckBox completedFilter;
    private final JCheckBox meetingFilter;
    private final JCheckBox deadlineFilter;

    private static final String NAME_OPTION_AZ = "Sort by Name (A-Z)";
    private static final String NAME_OPTION_ZA = "Sort by Name (Z-A)";
    private static final String DATE_OPTION_SOON = "Sort by Date (Upcoming-Later)";
    private static final String DATE_OPTION_LATER = "Sort by Date (Later-Upcoming)";

    // Constructor
    public EventListPanel() {
        setLayout(new BorderLayout());

        events = new ArrayList<>();

        // Panels for filters / sorting
        JPanel controlPanel = new JPanel();
        displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));

        // Sorting dropdown
        sortDropDown = new JComboBox<>(new String[]{NAME_OPTION_AZ, NAME_OPTION_ZA, DATE_OPTION_SOON, DATE_OPTION_LATER});
        sortDropDown.addActionListener(e -> updateEventDisplay());

        // Filter for completed events
        completedFilter = new JCheckBox("Filter Completed Events");
        completedFilter.addActionListener(e -> updateEventDisplay());

        // Filter for meetings
        meetingFilter = new JCheckBox("Filter Meetings");
        meetingFilter.addActionListener(e -> updateEventDisplay());

        // Filter for deadlines
        deadlineFilter = new JCheckBox("Filter Deadlines");
        deadlineFilter.addActionListener(e -> updateEventDisplay());

        // Add Event button
        JButton addEventButton = new JButton("Add Event");
        addEventButton.addActionListener(e -> showAddEventModal());

        // Add components to controlPanel
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

    void updateEventDisplay() {
        displayPanel.removeAll();

        // List for filtered events
        ArrayList<Event> filteredEvents = new ArrayList<>();

        // Apply filters
        for (Event event : events) {
            boolean shouldAdd = true;

            // Check completed filter
            if (completedFilter.isSelected() && event instanceof Completable && !((Completable) event).isComplete()) {
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

            // Add event
            if (shouldAdd) {
                filteredEvents.add(event);
            }
        }

        // Sort and display filtered events
        String selection = (String) sortDropDown.getSelectedItem();
        Comparator<Event> comparator = getComparator(selection);
        filteredEvents.sort(comparator);

        // Check if there are any events to display
        if (filteredEvents.isEmpty()) {
            displayPanel.add(new JLabel("No events to display."));
        } else {
            for (Event event : filteredEvents) {
                EventPanel eventPanel = new EventPanel(event);
                displayPanel.add(eventPanel);
            }
        }

        revalidate();
        repaint();
    }

    // "Flexible" sorting, comparator determines how Events will be ordered when sorting
    private Comparator<Event> getComparator(String selection) {
        selection = (String) sortDropDown.getSelectedItem();
        if (NAME_OPTION_AZ.equals(selection)) {
            return Comparator.comparing(Event::getName);
        } else if (NAME_OPTION_ZA.equals(selection)) {
            return Comparator.comparing(Event::getName).reversed();
        } else if (DATE_OPTION_SOON.equals(selection)) {
            return Comparator.comparing(Event::getDateTime);
        } else if (DATE_OPTION_LATER.equals(selection)) {
            return Comparator.comparing(Event::getDateTime).reversed();
        } else {
            throw new IllegalArgumentException("Invalid sort option: " + selection);
        }
    }

    private void showAddEventModal() {
        AddEventModal addEventModal = new AddEventModal(this);
        addEventModal.setVisible(true);
    }
}