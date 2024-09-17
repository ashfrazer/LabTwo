import javax.swing.*;
import java.util.ArrayList;

public class EventListPanel extends JPanel {
    ArrayList<Event> events;
    JPanel controlPanel = new JPanel();
    JPanel displayPanel = new JPanel();
    JComboBox<Event> sortDropDown = new JComboBox<Event>();
    JCheckBox filterDisplay = new JCheckBox();
    JButton addEventButton = new JButton();
}
