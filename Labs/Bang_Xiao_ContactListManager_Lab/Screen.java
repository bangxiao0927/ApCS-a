import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Screen extends JFrame implements ActionListener {
    private static final int DIVIDER_WIDTH = 50;
    private static final String FIRST_NAME_LABEL = "First Name";
    private static final String LAST_NAME_LABEL = "Last Name";
    private static final String USERNAME_LABEL = "Username";
    private static final String DOMAIN_NAME_LABEL = "Domain Name";
    private static final String DOMAIN_EXT_LABEL = "Domain Ext";

    // Instance variable - array of Contact class of size 10
    private Contact[] myList;
    private JTextArea displayArea;
    private JTextField searchField;
    private JButton firstNameBtn;
    private JButton lastNameBtn;
    private JButton usernameBtn;
    private JButton domainNameBtn;
    private JButton domainExtBtn;
    
    // Constructor
    public Screen() {
        // Initialize contact array with 10 contacts
        myList = new Contact[10];
        myList[0] = new Contact("John", "Smith", "john.smith@gmail.com");
        myList[1] = new Contact("Jane", "Doe", "janed@gmail.com");
        myList[2] = new Contact("George", "Washington", "gwashington@mvla.net");
        myList[3] = new Contact("Jennifer", "Smith", "1111111@mvla.net");
        myList[4] = new Contact("Michael", "Johnson", "mjohnson@yahoo.com");
        myList[5] = new Contact("Sarah", "Williams", "swilliams@outlook.com");
        myList[6] = new Contact("David", "Brown", "dbrown@stanford.edu");
        myList[7] = new Contact("Emily", "Davis", "emily.d@gmail.com");
        myList[8] = new Contact("Robert", "Miller", "rmiller@berkeley.edu");
        myList[9] = new Contact("Lisa", "Anderson", "landerson@yahoo.com");
        
        setupGUI();
        displayAllContacts();
    }
    
    // Set up the GUI components
    private void setupGUI() {
        setTitle("Contact List Search");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        // Create display area for contacts (displays 10 names and emails at all times)
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, BorderLayout.CENTER);
        
        // Create search panel with one search textfield
        JPanel searchPanel = new JPanel(new BorderLayout(5, 5));
        searchField = new JTextField();
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        searchPanel.add(new JLabel("Search: "), BorderLayout.WEST);
        searchPanel.add(searchField, BorderLayout.CENTER);
        
        // Create button panel with 5 buttons for search
        JPanel buttonPanel = new JPanel(new GridLayout(1, 5, 5, 5));
        firstNameBtn = new JButton("First Name");
        lastNameBtn = new JButton("Last Name");
        usernameBtn = new JButton("Username");
        domainNameBtn = new JButton("Domain Name");
        domainExtBtn = new JButton("Domain Ext");
        
        // Add action listeners to buttons
        firstNameBtn.addActionListener(this);
        lastNameBtn.addActionListener(this);
        usernameBtn.addActionListener(this);
        domainNameBtn.addActionListener(this);
        domainExtBtn.addActionListener(this);
        
        buttonPanel.add(firstNameBtn);
        buttonPanel.add(lastNameBtn);
        buttonPanel.add(usernameBtn);
        buttonPanel.add(domainNameBtn);
        buttonPanel.add(domainExtBtn);
        
        searchPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(searchPanel, BorderLayout.NORTH);
        
        setVisible(true);
    }
    
    // Display all 10 contacts
    private void displayAllContacts() {
        displayArea.setText("");
        appendHeader("All Contacts");
        for (Contact contact : myList) {
            displayArea.append(contact.toString() + "\n");
        }
    }
    
    // Handle button clicks
    public void actionPerformed(ActionEvent e) {
        String searchTerm = searchField.getText().trim();
        
        if (searchTerm.isEmpty()) {
            displayAllContacts();
            return;
        }

        if (e.getSource() == firstNameBtn) {
            searchContacts(FIRST_NAME_LABEL, searchTerm);
        } else if (e.getSource() == lastNameBtn) {
            searchContacts(LAST_NAME_LABEL, searchTerm);
        } else if (e.getSource() == usernameBtn) {
            searchContacts(USERNAME_LABEL, searchTerm);
        } else if (e.getSource() == domainNameBtn) {
            searchContacts(DOMAIN_NAME_LABEL, searchTerm);
        } else if (e.getSource() == domainExtBtn) {
            searchContacts(DOMAIN_EXT_LABEL, searchTerm);
        }
    }

    private void searchContacts(String label, String query) {
        displayArea.setText("");
        appendHeader("Search Results for " + label + ": " + query);

        boolean found = false;
        for (Contact contact : myList) {
            if (getFieldValue(contact, label).equalsIgnoreCase(query)) {
                displayArea.append(contact.toString() + "\n");
                found = true;
            }
        }

        if (!found) {
            displayArea.append("No contacts found.\n");
        }

        appendContactList();
    }

    private String getFieldValue(Contact contact, String label) {
        if (FIRST_NAME_LABEL.equals(label)) {
            return contact.getFirstName();
        } else if (LAST_NAME_LABEL.equals(label)) {
            return contact.getLastName();
        } else if (USERNAME_LABEL.equals(label)) {
            return contact.getEmailUsername();
        } else if (DOMAIN_NAME_LABEL.equals(label)) {
            return contact.getEmailDomainName();
        } else if (DOMAIN_EXT_LABEL.equals(label)) {
            return contact.getEmailDomainExtension();
        }
        return "";
    }

    private void appendContactList() {
        displayArea.append("\n");
        appendHeader("All Contacts");
        for (Contact contact : myList) {
            displayArea.append(contact.toString() + "\n");
        }
    }

    private void appendHeader(String title) {
        displayArea.append(title + "\n");
        appendDivider();
    }

    private void appendDivider() {
        for (int i = 0; i < DIVIDER_WIDTH; i++) {
            displayArea.append("=");
        }
        displayArea.append("\n");
    }
}
