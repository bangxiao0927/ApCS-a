//automatically call toString() when printing object if you specify the function

public class Split
{
    private String email;

    public Split(String email)
    {
        this.email = email;
    }

    public String getName()
    {
        int atIndex = email.indexOf("@");
        return email.substring(0, atIndex);
    }

    public String getDomain()
    {
        int atIndex = email.indexOf("@");
        int dotIndex = email.indexOf(".", atIndex);
        return email.substring(atIndex + 1, dotIndex);
    }

    public String getLastNameMVLA(String mvlaEmail)
    {
        int dotIndex = mvlaEmail.indexOf(".");
        int atIndex = mvlaEmail.indexOf("@");
        return mvlaEmail.substring(dotIndex + 1, atIndex);
    }

    public String toString()
    {
        return "address: " + email;
    }
}