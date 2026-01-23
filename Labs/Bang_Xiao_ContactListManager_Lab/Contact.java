public class Contact {
    private String firstName;
    private String lastName;
    private String email;

    public Contact(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + email + ")";
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailUsername() {
        return email.substring(0, email.indexOf('@'));
    }

    public String getEmailDomainName() {
        int atIndex = email.indexOf('@');
        int dotIndex = email.indexOf('.', atIndex);
        return email.substring(atIndex + 1, dotIndex);
    }

    public String getEmailDomainExtension() {
        int dotIndex = email.lastIndexOf('.');
        return email.substring(dotIndex + 1);
    }
}
