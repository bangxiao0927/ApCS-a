import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Screen extends JFrame implements ActionListener {
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
        displayArea.setText("All Contacts:\n");
        for (int i = 0; i < 50; i++) {
            displayArea.append("=");
        }
        displayArea.append("\n");
        
        for (int i = 0; i < myList.length; i++) {
            displayArea.append(myList[i].toString() + "\n");
        }
    }
    
    // Handle button clicks
    public void actionPerformed(ActionEvent e) {
        String searchTerm = searchField.getText().trim();
        
        if (e.getSource() == firstNameBtn) {
            searchByFirstName(searchTerm);
        } else if (e.getSource() == lastNameBtn) {
            searchByLastName(searchTerm);
        } else if (e.getSource() == usernameBtn) {
            searchByUsername(searchTerm);
        } else if (e.getSource() == domainNameBtn) {
            searchByDomainName(searchTerm);
        } else if (e.getSource() == domainExtBtn) {
            searchByDomainExtension(searchTerm);
        }
    }
    
    // Search for contacts by first name
    private void searchByFirstName(String query) {
        displayArea.setText("Search Results for First Name: " + query + "\n");
        for (int i = 0; i < 50; i++) {
            displayArea.append("=");
        }
        displayArea.append("\n");
        
        int count = 0;
        for (int i = 0; i < myList.length; i++) {
            if (myList[i].getFirstName().equalsIgnoreCase(query)) {
                displayArea.append(myList[i].toString() + "\n");
                count++;
            }
        }
        
        if (count == 0) {
            displayArea.append("No contacts found.\n");
        }
        
        displaySeparatorAndAllContacts();
    }
    
    // Search for contacts by last name
    private void searchByLastName(String query) {
        displayArea.setText("Search Results for Last Name: " + query + "\n");
        for (int i = 0; i < 50; i++) {
            displayArea.append("=");
        }
        displayArea.append("\n");
        
        int count = 0;
        for (int i = 0; i < myList.length; i++) {
            if (myList[i].getLastName().equalsIgnoreCase(query)) {
                displayArea.append(myList[i].toString() + "\n");
                count++;
            }
        }
        
        if (count == 0) {
            displayArea.append("No contacts found.\n");
        }
        
        displaySeparatorAndAllContacts();
    }
    
    // Search for contacts by username
    private void searchByUsername(String query) {
        displayArea.setText("Search Results for Username: " + query + "\n");
        for (int i = 0; i < 50; i++) {
            displayArea.append("=");
        }
        displayArea.append("\n");
        
        int count = 0;
        for (int i = 0; i < myList.length; i++) {
            if (myList[i].getEmailUsername().equalsIgnoreCase(query)) {
                displayArea.append(myList[i].toString() + "\n");
                count++;
            }
        }
        
        if (count == 0) {
            displayArea.append("No contacts found.\n");
        }
        
        displaySeparatorAndAllContacts();
    }
    
    // Search for contacts by domain name
    private void searchByDomainName(String query) {
        displayArea.setText("Search Results for Domain Name: " + query + "\n");
        for (int i = 0; i < 50; i++) {
            displayArea.append("=");
        }
        displayArea.append("\n");
        
        int count = 0;
        for (int i = 0; i < myList.length; i++) {
            if (myList[i].getEmailDomainName().equalsIgnoreCase(query)) {
                displayArea.append(myList[i].toString() + "\n");
                count++;
            }
        }
        
        if (count == 0) {
            displayArea.append("No contacts found.\n");
        }
        
        displaySeparatorAndAllContacts();
    }
    
    // Search for contacts by domain extension
    private void searchByDomainExtension(String query) {
        displayArea.setText("Search Results for Domain Extension: " + query + "\n");
        for (int i = 0; i < 50; i++) {
            displayArea.append("=");
        }
        displayArea.append("\n");
        
        int count = 0;
        for (int i = 0; i < myList.length; i++) {
            if (myList[i].getEmailDomainExtension().equalsIgnoreCase(query)) {
                displayArea.append(myList[i].toString() + "\n");
                count++;
            }
        }
        
        if (count == 0) {
            displayArea.append("No contacts found.\n");
        }
        
        displaySeparatorAndAllContacts();
    }
    
    // Display separator and all contacts at bottom
    private void displaySeparatorAndAllContacts() {
        displayArea.append("\n");
        for (int i = 0; i < 50; i++) {
            displayArea.append("=");
        }
        displayArea.append("\n");
        displayArea.append("All Contacts:\n");
        for (int i = 0; i < 50; i++) {
            displayArea.append("=");
        }
        displayArea.append("\n");
        
        for (int i = 0; i < myList.length; i++) {
            displayArea.append(myList[i].toString() + "\n");
        }
    }
}