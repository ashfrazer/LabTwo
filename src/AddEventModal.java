import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

public class AddEventModal extends JDialog {
    private EventListPanel eventListPanel;
    private JTextField nameField;
    private JTextField locationField;
    private JSpinner startDateSpinner;
    private JSpinner endDateSpinner;
    private JComboBox<String> eventTypeComboBox;

    public AddEventModal(EventListPanel eventListPanel) {
        this.eventListPanel = eventListPanel;
        setTitle("Add Event");
        setSize(400, 300);
        setLayout(new GridLayout(6,2));

        nameField = new JTextField();
        locationField = new JTextField();
        startDateSpinner = new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.MINUTE));
        endDateSpinner = new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.MINUTE));
        eventTypeComboBox = new JComboBox<>(new String[]{"Deadline", "Meeting"});

        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Location:"));
        add(locationField);
        add(new JLabel("Start Date:"));
        add(startDateSpinner);
        add(new JLabel("End Date:"));
        add(endDateSpinner);
        add(new JLabel("Event Type:"));
        add(eventTypeComboBox);

        JButton addButton = new JButton("Add Event");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEvent();
                dispose();
            }
        });

        add(addButton);
    }

    private void addEvent() {
        String name = nameField.getText();
        String location = locationField.getText();
        Date startDate = (Date) startDateSpinner.getValue();
        Date endDate = (Date) endDateSpinner.getValue();
        String eventType = (String) eventTypeComboBox.getSelectedItem();

        if ("Meeting".equals(eventType)) {
            Meeting meeting = new Meeting(name, startDate, endDate, location);
            eventListPanel.addEvent(meeting);
        } else {
            Deadline deadline = new Deadline(name, startDate);
            eventListPanel.addEvent(deadline);
        }
    }
}
